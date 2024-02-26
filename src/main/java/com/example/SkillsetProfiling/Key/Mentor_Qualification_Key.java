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
public class Mentor_Qualification_Key implements Serializable {
    private Integer MentorID;
    private Integer QualificationID;
    private Integer DomainID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mentor_Qualification_Key that = (Mentor_Qualification_Key) o;
        return Objects.equals(MentorID, that.MentorID) &&
                Objects.equals(QualificationID, that.QualificationID) &&
                Objects.equals(DomainID, that.DomainID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MentorID, QualificationID, DomainID);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d, %d)", MentorID, QualificationID, DomainID);
    }


}
