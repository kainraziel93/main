package com.lms.course.chain.of.responsability.filter;

import java.util.List;

import com.lms.course.entity.Course;

public abstract class FilterChain {

	private FilterChain nextFilter;
	
	public abstract List<Course> filter();

	public FilterChain getNextFilter() {
		return nextFilter;
	}

	public void setNextFilter(FilterChain nextFilter) {
		this.nextFilter = nextFilter;
	}
	
	
}
