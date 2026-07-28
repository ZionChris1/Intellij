import java.util.Objects;

public class Cat implements Comparable<Cat>{
    private String mName;
    private String mBreed;
    private int mAge;

    public Cat(String name, String breed, int age) {
        mName = name;
        mBreed = breed;
        mAge = age;
    }

    public String getName() {
        return mName;
    }

    public String getBreed() {
        return mBreed;
    }

    public int getAge() {
        return mAge;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setBreed(String breed) {
        mBreed = breed;
    }

    public void setAge(int age) {
        mAge = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return mAge == cat.mAge && Objects.equals(mName, cat.mName) && Objects.equals(mBreed, cat.mBreed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mName, mBreed, mAge);
    }

    @Override
    public String toString() {
        return "Cat [" +
                "Name=" + mName +
                ", Breed=" + mBreed +
                ", Age=" + mAge +
                ", Human Age=" + ageInHumanYears() +
                ']';
    }

    public int ageInHumanYears() {
        if(mAge == 1)
            return 1;
        else if(mAge == 2)
            return 24;
        else
            return 24 + (mAge - 2) * 4;
    }

    @Override
    public int compareTo(Cat other) {
        //Compare ages
        int ageComp = mAge - other.mAge;
        if(ageComp != 0) return ageComp;

        //If ages are identical compare names
        int nameComp = mName.compareTo(other.mName);
        if(nameComp != 0) return nameComp;

        //If ages names are identical compare Breed
        return mBreed.compareTo(other.mBreed);
    }
}
