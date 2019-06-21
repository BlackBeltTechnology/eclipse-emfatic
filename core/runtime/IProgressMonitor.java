package org.eclipse.core.runtime;


public interface IProgressMonitor {
    int UNKNOWN = -1;

    void beginTask(String var1, int var2);

    void done();

    void internalWorked(double var1);

    boolean isCanceled();

    void setCanceled(boolean var1);

    void setTaskName(String var1);

    void subTask(String var1);

    void worked(int var1);
}
