package com.application;

/**
 * This class defines several interfaces to be used with JSONView annotation.
 * These are used to prevent infinite recursion while allowing any entity returned from a controller 
 * to display the relevant sections of the entity graph it is connected to.
 * @author Tim Schommer
 *
 */
public class View {
	/**
	 * An interface for use with JsonView. Fields allowed to be visible when returning a Tag
	 * are given this view. Controller methods that return a Tag are marked with this view.
	 * @author Tim Schommer
	 *
	 */
	public interface TagView{}
	
	/**
	 * An interface for use with JsonView. Fields allowed to be visible when returning a SkillLevel
	 * are given this view. Controller methods that return a SkillLevel are marked with this view.
	 * @author Tim Schommer
	 *
	 */
	public interface SkillLevelView{}
	
	/**
	 * An interface for use with JsonView. Fields allowed to be visible when returning a Post
	 * are given this view. Controller methods that return a Post are marked with this view.
	 * @author Tim Schommer
	 *
	 */
	public interface PostView{}
	
	/**
	 * An interface for use with JsonView. Fields allowed to be visible when returning a User
	 * are given this view. Controller methods that return a User are marked with this view.
	 * @author Tim Schommer
	 *
	 */
	public interface UserView{}
	
	/**
	 * An interface for use with JsonView. Fields allowed to be visible when returning a State
	 * are given this view. Controller methods that return a State are marked with this view.
	 * @author Tim Schommer
	 *
	 */
	public interface StateView{}
}
