import java.io.Serializable;
import java.util.Objects;

public class Task implements Serializable {
    private String mName, mDueDate, mDeadline, mPriority;

    public Task(String name, String dueDate, String deadline, String priority) {
        mName = name;
        mDueDate = dueDate;
        mDeadline = deadline;
        mPriority = priority;
    }

    public Task(Task task) {
        mName = task.mName;
        mDueDate = task.mDueDate;
        mDeadline = task.mDeadline;
        mPriority = task.mPriority;
    }

    public String getDeadline() {
        return mDeadline;
    }

    public void setDeadline(String deadline) {
        mDeadline = deadline;
    }

    public String getPriority() {
        return mPriority;
    }

    public void setPriority(String priority) {
        mPriority = priority;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDueDate() {
        return mDueDate;
    }

    public void setDueDate(String dueDate) {
        mDueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Task ["
                + "name=" + mName
                + ", dueDate=" + mDueDate
                + ", deadline=" + mDeadline
                + ", priority=" + mPriority
                + ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return mPriority == task.mPriority && Objects.equals(mName, task.mName) && Objects.equals(mDueDate, task.mDueDate) && Objects.equals(mDeadline, task.mDeadline);
    }
}
