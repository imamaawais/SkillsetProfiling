package com.example.SkillsetProfiling.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Test")
public class Test {

    @Id
    private Integer TestID;
    private Integer studentSkillLevelID;
    private Integer test_number;
    private Boolean is_passed;
    private Integer no_of_attempts;

}
