/*******************************************************************************
 * Copyright (c) 2004, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.emf.emfatic.core.generator.ecore;

/*-
 * #%L
 * Eclipse :: Emfatic
 * %%
 * Copyright (C) 2018 - 2023 BlackBelt Technology
 * %%
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is
 * available at https://www.gnu.org/software/classpath/license.html.
 * 
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 * #L%
 */

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.emfatic.core.generics.util.OneToOneMap;
import org.eclipse.emf.emfatic.core.lang.gen.ast.*;
import org.eclipse.emf.emfatic.core.util.EmfaticAnnotationMap;
import org.eclipse.gymnast.runtime.core.ast.ASTNode;
import org.eclipse.gymnast.runtime.core.parser.ParseContext;

import java.io.IOException;


public class Builder extends GenerationPhase {

	private EmfaticAnnotationMap _annotationMap;
	private OneToOneMap<ASTNode, EObject> cstDecl2EcoreAST = new OneToOneMap<ASTNode, EObject>();
	

	public Builder() {
	}

	public void build(ParseContext parseContext, Resource resource) throws IOException {
		cstDecl2EcoreAST.clear();
		initParseContext(parseContext);
		_annotationMap = new EmfaticAnnotationMap();
		CompUnit compUnit = (CompUnit) parseContext.getParseRoot();
		if (compUnit != null) {
			buildPackage(compUnit, resource);
		}
	}

	private void buildPackage(CompUnit compUnit, Resource resource) {
		PackageDecl packageDecl = compUnit.getPackageDecl();
		if (packageDecl != null) {
			String name = getIDText(packageDecl.getName());
			buildPackage(name, packageDecl.getAnnotations(), compUnit.getTopLevelDecls(), resource.getContents());
		}
	}

	private void buildPackage(SubPackageDecl packageDecl, EPackage parentPackage) {
		String name = getIDText(packageDecl.getName());
		buildPackage(name, packageDecl.getAnnotations(), packageDecl.getTopLevelDecls(), parentPackage
				.getESubpackages());
	}

	private void buildPackage(String name, Annotations annotations, TopLevelDecls topLevelDecls, EList parentContents) {
		final EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
		cstDecl2EcoreAST.put(topLevelDecls.getParent(), ePackage);
		ePackage.setName(name);
		buildAnnotations(annotations, ePackage);
		parentContents.add(ePackage);
		(new EmfaticASTNodeVisitor() {

			public boolean beginVisit(SubPackageDecl subPackageDecl) {
				if (!isDuplicateName(ePackage, subPackageDecl.getName()))
					buildPackage(subPackageDecl, ePackage);
				return false;
			}

			public boolean beginVisit(ClassDecl classDecl) {
				if (!isDuplicateName(ePackage, classDecl.getName()))
					buildClass(classDecl, ePackage);
				return false;
			}

			public boolean beginVisit(EnumDecl enumDecl) {
				if (!isDuplicateName(ePackage, enumDecl.getName()))
					buildEnum(enumDecl, ePackage);
				return false;
			}

			public boolean beginVisit(DataTypeDecl dataTypeDecl) {
				if (!isDuplicateName(ePackage, dataTypeDecl.getName()))
					buildDataType(dataTypeDecl, ePackage);
				return false;
			}

			public boolean beginVisit(MapEntryDecl mapEntryDecl) {
				if (!isDuplicateName(ePackage, mapEntryDecl.getName()))
					buildMapEntry(mapEntryDecl, ePackage);
				return false;
			}

		}).visit(topLevelDecls);
	}

	private boolean isDuplicateName(EPackage containingPackage, EmfaticTokenNode nameTokenNode) {
		String name = getIDText(nameTokenNode);
		if (getSubPackage(containingPackage, name) != null) {
			logError(new EmfaticSemanticError.DuplicatePackageMemberDeclaration(nameTokenNode));
			return true;
		}
		if (containingPackage.getEClassifier(name) != null) {
			logError(new EmfaticSemanticError.DuplicatePackageMemberDeclaration(nameTokenNode));
			return true;
		} else {
			return false;
		}
	}

	private void buildClass(ClassDecl classDecl, EPackage ePackage) {
		final EClass eClass = EcoreFactory.eINSTANCE.createEClass();
		cstDecl2EcoreAST.put(classDecl, eClass);
		String name = getIDText(classDecl.getName());
		eClass.setName(name);
		boolean isAbstract = classDecl.getAbstractModifier() != null;
		eClass.setAbstract(isAbstract);
		boolean isInterface = "interface".equals(classDecl.getClassKind().getText());
		eClass.setInterface(isInterface);
		if (classDecl.getInstClassName() != null) {
			String instClassName = getValue(classDecl.getInstClassName());
			eClass.setInstanceClassName(instClassName);
		}
		buildAnnotations(classDecl.getAnnotations(), eClass);
		ePackage.getEClassifiers().add(eClass);

		processTypeParamVarsInEClass(classDecl, eClass);

		(new EmfaticASTNodeVisitor() {

			public boolean beginVisit(Attribute attribute) {
				if (isDuplicateName(eClass, attribute.getName())) {
					return false;
				} else {
					EAttribute eAttr = EcoreFactory.eINSTANCE.createEAttribute();
					cstDecl2EcoreAST.put(attribute, eAttr);
					String attrName = getIDText(attribute.getName());
					eAttr.setName(attrName);
					processModifiers(attribute.getModifiers(), eAttr);
					setMultiplicity(attribute.getTypeWithMulti().getMultiplicity(), eAttr);
					eClass.getEStructuralFeatures().add(eAttr);
					if (attribute.getDefaultValueExpr() != null) {
						String dv = TokenText.Get(attribute.getDefaultValueExpr());
						eAttr.setDefaultValueLiteral(dv);
					}
					buildAnnotations(attribute.getAnnotations(), eAttr);
					return false;
				}
			}

			public boolean beginVisit(Reference reference) {
				if (isDuplicateName(eClass, reference.getName())) {
					return false;
				} else {
					EReference eRef = EcoreFactory.eINSTANCE.createEReference();
					cstDecl2EcoreAST.put(reference, eRef);
					String refName = getIDText(reference.getName());
					eRef.setName(refName);
					processModifiers(reference.getModifiers(), eRef);
					setMultiplicity(reference.getTypeWithMulti().getMultiplicity(), eRef);
					eClass.getEStructuralFeatures().add(eRef);
					buildAnnotations(reference.getAnnotations(), eRef);
					return false;
				}
			}

			public boolean beginVisit(Operation operation) {
				buildOperation(operation, eClass);
				return false;
			}

		}).visit(classDecl.getClassMemberDecls());

	}

	/**
	 * processing of the bounds of type params has to be delayed as some of them
	 * may refer to EClass'es not yet added to the EPackage. The type variables
	 * (but not their bounds) are added now however as they may be needed to
	 * correctly determine usages-declarations: a type variable with the same
	 * name as an EClassifier effectively hides it. thus explaining why type
	 * vars are added to the EClass before any attr or ref or op.
	 */
	private void processTypeParamVarsInEClass(ClassDecl classDecl, final EClass eClass) {
		if (classDecl.getTypeParamsInfo() == null) {
			return;
		}
		OneOrMoreTypeParams oomtps = classDecl.getTypeParamsInfo().getOneOrMoreTypeParams();
		if (oomtps == null) {
			return;
		}
		for (ASTNode n : oomtps.getChildren()) {
			if (n instanceof TypeParam) {
				TypeParam tp = (TypeParam) n;
				EmfaticTokenNode nameTokenNode = tp.getTypeVarName();
				if (isDuplicateTypeVarName(eClass.getETypeParameters(), nameTokenNode)) {
					logError(new EmfaticSemanticError.DuplicateTypeVariableName(nameTokenNode));
				} else {
					ETypeParameter etp = EcoreFactory.eINSTANCE.createETypeParameter();
					cstDecl2EcoreAST.put(tp, etp);
					String tvName = getIDText(tp.getTypeVarName());
					etp.setName(tvName);
					eClass.getETypeParameters().add(etp);
				}
			}
		}
	}

	private void processTypeParamVarsInEOperation(Operation oDecl, final EOperation eOp) {
		if (oDecl.getTypeParamsInfo() == null) {
			return;
		}
		OneOrMoreTypeParams oomtps = oDecl.getTypeParamsInfo().getOneOrMoreTypeParams();
		if (oomtps == null) {
			return;
		}
		for (ASTNode n : oomtps.getChildren()) {
			if (n instanceof TypeParam) {
				TypeParam tp = (TypeParam) n;
				EmfaticTokenNode nameTokenNode = tp.getTypeVarName();
				// look out for duplicates among other operation-level type
				// params
				if (isDuplicateTypeVarName(eOp.getETypeParameters(), nameTokenNode)) {
					logError(new EmfaticSemanticError.DuplicateTypeVariableName(nameTokenNode));
				} else {
					// look out for duplicates among class-level type params
					if (isDuplicateTypeVarName(eOp.getEContainingClass().getETypeParameters(), nameTokenNode)) {
						logError(new EmfaticSemanticError.DuplicateTypeVariableName(nameTokenNode));
					} else {
						ETypeParameter etp = EcoreFactory.eINSTANCE.createETypeParameter();
						cstDecl2EcoreAST.put(tp, etp);
						String tvName = getIDText(tp.getTypeVarName());
						etp.setName(tvName);
						eOp.getETypeParameters().add(etp);
					}
				}
			}
		}
	}

	private boolean isDuplicateTypeVarName(EList<ETypeParameter> typeParameters, EmfaticTokenNode nameTokenNode) {
		String tvName = getIDText(nameTokenNode);
		for (ETypeParameter tp : typeParameters) {
			if (tp.getName().equals(tvName)) {
				logError(new EmfaticSemanticError.DuplicateTypeVariableName(nameTokenNode));
				return true;
			}
		}
		return false;
	}

	private boolean isDuplicateName(EClass containingClass, EmfaticTokenNode nameTokenNode) {
		String name = getIDText(nameTokenNode);
		if (containingClass.getEStructuralFeature(name) != null) {
			logError(new EmfaticSemanticError.DuplicateClassStructuralFeatureDeclaration(nameTokenNode));
			return true;
		} else {
			return false;
		}
	}

	private void buildOperation(Operation operation, EClass eClass) {
		final EOperation eOp = EcoreFactory.eINSTANCE.createEOperation();
		cstDecl2EcoreAST.put(operation, eOp);
		String opName = getIDText(operation.getName());
		eOp.setName(opName);
		processModifiers(operation.getModifiers(), eOp);
		if (operation.getResType() instanceof TypeWithMulti) {
			TypeWithMulti twm = (TypeWithMulti) operation.getResType();
			Multiplicity opMulti = twm.getMultiplicity();
			setMultiplicity(opMulti, eOp);
		}
		eClass.getEOperations().add(eOp);
		buildAnnotations(operation.getAnnotations(), eOp);
		if (operation.getParams() != null)
			(new EmfaticASTNodeVisitor() {

				public boolean beginVisit(Param param) {
					EParameter eParam = EcoreFactory.eINSTANCE.createEParameter();
					cstDecl2EcoreAST.put(param, eParam);
					String paramName = getIDText(param.getName());
					eParam.setName(paramName);
					processModifiers(param.getModifiers(), eParam);
					setMultiplicity(param.getTypeWithMulti().getMultiplicity(), eParam);
					eOp.getEParameters().add(eParam);
					buildAnnotations(param, eParam);
					return false;
				}

			}).visit(operation.getParams());
		processTypeParamVarsInEOperation(operation, eOp);
	}

	private void buildEnum(EnumDecl enumDecl, EPackage ePackage) {
		final EEnum eEnum = EcoreFactory.eINSTANCE.createEEnum();
		cstDecl2EcoreAST.put(enumDecl, eEnum);
		final int lastVal[] = { -1 };
		String name = getIDText(enumDecl.getName());
		eEnum.setName(name);
		buildAnnotations(enumDecl.getAnnotations(), eEnum);
		ePackage.getEClassifiers().add(eEnum);
		(new EmfaticASTNodeVisitor() {

			public boolean beginVisit(EnumLiteral enumLiteral) {
				EEnumLiteral eLit = EcoreFactory.eINSTANCE.createEEnumLiteral();
				cstDecl2EcoreAST.put(enumLiteral, eLit);
				String literalName = getIDText(enumLiteral.getName());
				eLit.setName(literalName);
				int value;
				if (enumLiteral.getVal() == null)
					value = lastVal[0] + 1;
				else
					value = Integer.parseInt(enumLiteral.getVal().getText());
				eLit.setValue(value);
				lastVal[0] = value;
				buildAnnotations(enumLiteral, eLit);
				eEnum.getELiterals().add(eLit);
				return false;
			}

		}).visit(enumDecl.getEnumLiterals());
	}

	private void buildDataType(DataTypeDecl dataTypeDecl, EPackage ePackage) {
		EDataType eDataType = EcoreFactory.eINSTANCE.createEDataType();
		cstDecl2EcoreAST.put(dataTypeDecl, eDataType);
		String name = getIDText(dataTypeDecl.getName());
		eDataType.setName(name);
		boolean isSerializable = dataTypeDecl.getTransientModifier() == null;
		eDataType.setSerializable(isSerializable);
		String instClassName = getValue(dataTypeDecl.getInstClassName());
		eDataType.setInstanceClassName(instClassName);
		buildAnnotations(dataTypeDecl.getAnnotations(), eDataType);
		ePackage.getEClassifiers().add(eDataType);
	}

	private void buildMapEntry(MapEntryDecl mapEntryDecl, EPackage ePackage) {
		EClass eClass = EcoreFactory.eINSTANCE.createEClass();
		cstDecl2EcoreAST.put(mapEntryDecl, eClass);
		String name = getIDText(mapEntryDecl.getName());
		eClass.setName(name);
		eClass.setInstanceClassName("java.util.Map$Entry");
		buildAnnotations(mapEntryDecl.getAnnotations(), eClass);
		ePackage.getEClassifiers().add(eClass);
	}

	private void buildAnnotations(EmfaticASTNode node, final EModelElement eModelElement) {
		(new EmfaticASTNodeVisitor() {

			public boolean beginVisit(Annotation annotation) {
				String literalSourceURI = getValue(annotation.getSource());
				if ("namespace".equals(literalSourceURI.toLowerCase()) && (eModelElement instanceof EPackage)) {
					processPackageNamespaceAnnotation(annotation, (EPackage) eModelElement);
				} else {
					EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
					cstDecl2EcoreAST.put(annotation, eAnnotation);
					String mappedSourceURI = _annotationMap.mapSourceURI(literalSourceURI);
					if (mappedSourceURI != null)
						eAnnotation.setSource(mappedSourceURI);
					else
						eAnnotation.setSource(literalSourceURI);
					eModelElement.getEAnnotations().add(eAnnotation);
					if ("http://www.eclipse.org/emf/2004/EmfaticAnnotationMap".equals(eAnnotation.getSource()))
						updateAnnotationMap(annotation, eAnnotation);
					else
						processAnnotation(annotation, eAnnotation, literalSourceURI);
				}
				return false;
			}

		}).visit(node);
	}

	private void updateAnnotationMap(Annotation annotation, final EAnnotation eAnnotation) {
		(new EmfaticASTNodeVisitor() {

			public boolean beginVisit(KeyEqualsValue keyEqualsValue) {
				String key = getValue(keyEqualsValue.getKey());
				String value = getValue(keyEqualsValue.getValue());
				String label = _annotationMap.addMapping(key, value, keyEqualsValue, Builder.this);
				if (label != null)
					if (value != null)
						eAnnotation.getDetails().put(key, value);
					else
						eAnnotation.getDetails().put(key, label);
				return false;
			}

		}).visit(annotation);
	}

	private void processAnnotation(Annotation annotation, final EAnnotation eAnnotation, String literalSourceURI) {
		final int temp[] = new int[1];
		(new EmfaticASTNodeVisitor() {

			public boolean beginVisit(KeyEqualsValue keyEqualsValue) {
				temp[0]++;
				return false;
			}

		}).visit(annotation);
		final int paramCount = temp[0];
		(new EmfaticASTNodeVisitor() {

			public boolean beginVisit(KeyEqualsValue keyEqualsValue) {
				paramIndex++;
				String key;
				String value;
				if (keyEqualsValue.getEquals() != null) {
					key = getValue(keyEqualsValue.getKey());
					value = getValue(keyEqualsValue.getValue());
				} else {
					key = _annotationMap.getImplicitKeyName(eAnnotation.getSource(), paramIndex, paramCount);
					if (key == null) {
						key = "value";
						if (paramIndex > 0)
							key = key + Integer.toString(paramIndex + 1);
						logWarning(new EmfaticSemanticWarning.UnknownAttributeImplicitKey(keyEqualsValue, key));
					}
					value = getValue(keyEqualsValue.getKey());
				}
				eAnnotation.getDetails().put(key, value);
				return false;
			}

			int paramIndex;

			{
				paramIndex = -1;
			}
		}).visit(annotation);
	}

	private void processPackageNamespaceAnnotation(Annotation namespaceAnnotation, final EPackage ePackage) {
	    
	    new EmfaticASTNodeVisitor() {
	        
            public boolean beginVisit(KeyEqualsValue keyEqualsValue) {
                String key = getValue(keyEqualsValue.getKey()).toLowerCase();
                String value = getValue(keyEqualsValue.getValue());
                
                if (EmfaticAnnotationMap.EPACKAGE_NSPREFIX_KEY.equals(key)) {
                    ePackage.setNsPrefix(value);
                }
                else if (EmfaticAnnotationMap.EPACKAGE_NSURI_KEY.equals(key)) {
                    ePackage.setNsURI(value);
                }
                else {
                    logWarning(new EmfaticSemanticWarning.UnknownEPackageNamespaceAttributeKey(keyEqualsValue));
                }
                
                return false;
            }
            
	    }.visit(namespaceAnnotation);
	    
	}

	
	//
	// Modifiers
	//
	
	private void processModifiers(Modifiers modifiers, final ETypedElement eTypedElement) {
	    new EmfaticASTNodeVisitor() {
	        public boolean beginVisit(OptNegatedModifier optNegatedModifier) {
	            boolean modifierValue = (optNegatedModifier.getBang() == null);
	            String modifierText = optNegatedModifier.getModifier().getText();
	            
	            if (modifierText.equals(Modifier.KW_UNIQUE)) {
	                eTypedElement.setUnique(modifierValue);
	            }
	            else if (modifierText.equals(Modifier.KW_ORDERED)) {
	                eTypedElement.setOrdered(modifierValue);
	            }
	            
	            return false;
	        }
	    }.visit(modifiers);
	    
	    if (eTypedElement instanceof EStructuralFeature) {
	        processModifiersHelper(modifiers, (EStructuralFeature)eTypedElement);
	    }
	}

	private void processModifiersHelper(Modifiers modifiers, final EStructuralFeature eStructuralFeature) {
	    new EmfaticASTNodeVisitor() {
	        public boolean beginVisit(OptNegatedModifier optNegatedModifier) {
	            boolean modifierValue = (optNegatedModifier.getBang() == null);
	            String modifierText = optNegatedModifier.getModifier().getText();
	            
	            if (modifierText.equals(Modifier.KW_READONLY)) {
	                eStructuralFeature.setChangeable(!modifierValue);
	            }
	            else if (modifierText.equals(Modifier.KW_VOLATILE)) {
	                eStructuralFeature.setVolatile(modifierValue);
	            }
	            else if (modifierText.equals(Modifier.KW_TRANSIENT)) {
	                eStructuralFeature.setTransient(modifierValue);
	            }
	            else if (modifierText.equals(Modifier.KW_UNSETTABLE)) {
	                eStructuralFeature.setUnsettable(modifierValue);
	            }
	            else if (modifierText.equals(Modifier.KW_DERIVED)) {
	                eStructuralFeature.setDerived(modifierValue);
	            }
	            else if (modifierText.equals(Modifier.KW_ID)) {
	                if (eStructuralFeature instanceof EAttribute) {
	                    EAttribute eAttribute = (EAttribute)eStructuralFeature;
	                    eAttribute.setID(modifierValue);
	                }
	            }
	            else if (modifierText.equals(Modifier.KW_RESOLVE)) {
	                if (eStructuralFeature instanceof EReference) {
	                    EReference eReference = (EReference)eStructuralFeature;
	                    eReference.setResolveProxies(modifierValue);
	                }
	            }
	            
	            return false;
	        }
	    }.visit(modifiers);
	    
	}

		protected OneToOneMap<ASTNode, EObject> getCstDecl2EcoreASTMap() {
		return cstDecl2EcoreAST;
	}

}
