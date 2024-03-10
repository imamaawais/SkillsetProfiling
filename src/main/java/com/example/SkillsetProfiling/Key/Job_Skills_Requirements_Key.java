package com.example.SkillsetProfiling.Key;

import com.example.SkillsetProfiling.Entity.Skill_Level;
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
public class Job_Skills_Requirements_Key implements Serializable {
    private Integer JobID;
    private Integer SkillID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job_Skills_Requirements_Key that = (Job_Skills_Requirements_Key) o;
        return Objects.equals(JobID, that.JobID) && Objects.equals(SkillID, that.SkillID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(JobID, SkillID);
    }

    @Override
    public String toString() {
        return "Job_Eligibility_Check_Key{" +
                "JobID=" + JobID +
                ", StudentID=" + SkillID +
                '}';
    }
}
