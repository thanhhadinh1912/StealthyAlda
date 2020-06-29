package com.stealthyalda.ai.model.dtos;

public class HardskillDTO {
    private String hardSkillName;
    private int hardSkillId;

    public String getHardSkillName() {
        return hardSkillName;
    }

    public void setHardSkillName(String hardSkillName) {
        this.hardSkillName = hardSkillName;
    }

    public int getHardSkillId() {
        return hardSkillId;
    }

    public void setHardSkillId(int hardSkillId) {
        this.hardSkillId = hardSkillId;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof HardskillDTO)) {
            return false;
        }
        HardskillDTO c = (HardskillDTO) o;
        return Integer.compare(c.hardSkillId, this.hardSkillId) == 0
                && this.getHardSkillName().equals(c.getHardSkillName());

    }
}
