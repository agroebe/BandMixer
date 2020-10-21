package com.application;

public class View {
	public interface TagView{}
	public interface SkillLevelView{}
	public interface PostView{}
	
	public interface TagSkill extends TagView, SkillLevelView{}
	public interface TagPost extends TagView, PostView{}
	public interface SkillPost extends SkillLevelView, PostView{}
	public interface AllView extends TagView, PostView, SkillLevelView{}
}
