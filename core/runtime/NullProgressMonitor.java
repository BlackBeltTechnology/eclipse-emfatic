package org.eclipse.core.runtime;

public class NullProgressMonitor implements IProgressMonitor {
    private volatile boolean cancelled = false;

    public NullProgressMonitor() {
    }

    public void beginTask(String name, int totalWork) {
    }

    public void done() {
    }

    public void internalWorked(double work) {
    }

    public boolean isCanceled() {
        return this.cancelled;
    }

    public void setCanceled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void setTaskName(String name) {
    }

    public void subTask(String name) {
    }

    public void worked(int work) {
    }
}
