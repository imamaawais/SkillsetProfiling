package com.example.SkillsetProfiling.Key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student_Skills_Key implements Serializable {
    private Integer StudentID;
    private Integer SkillID;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student_Skills_Key that = (Student_Skills_Key) o;
        return Objects.equals(StudentID, that.StudentID) &&
                Objects.equals(SkillID, that.SkillID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(StudentID, SkillID);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", StudentID, SkillID);
    }
}
