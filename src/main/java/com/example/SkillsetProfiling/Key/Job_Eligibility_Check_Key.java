package com.example.SkillsetProfiling.Key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Job_Eligibility_Check_Key implements Serializable {
    private Integer JobID;
    private Integer StudentID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job_Eligibility_Check_Key that = (Job_Eligibility_Check_Key) o;
        return Objects.equals(JobID, that.JobID) && Objects.equals(StudentID, that.StudentID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(JobID, StudentID);
    }

    @Override
    public String toString() {
        return "Job_Eligibility_Check_Key{" +
                "JobID=" + JobID +
                ", StudentID=" + StudentID +
                '}';
    }

}
