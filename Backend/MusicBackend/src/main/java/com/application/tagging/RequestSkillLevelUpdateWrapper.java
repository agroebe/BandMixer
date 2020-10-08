package com.application.tagging;

import javax.validation.Valid;

public class RequestSkillLevelUpdateWrapper {
    @Valid private RequestSkillLevelUpdate level;

    public RequestSkillLevelUpdate getLevel(){
        return level;
    }

    public void setLevel(RequestSkillLevelUpdate level){
        this.level = level;
    }
}
