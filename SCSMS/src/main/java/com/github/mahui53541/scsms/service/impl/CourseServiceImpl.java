package com.github.mahui53541.scsms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.github.mahui53541.scsms.domain.Course;
import com.github.mahui53541.scsms.domain.CourseCatalog;
import com.github.mahui53541.scsms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.DEFAULT)
@Service("courseService")
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseCatalog courseCatalog;
	@Override
	public List<HashMap<String, String>> queryCourse() {
		List<HashMap<String, String>> list=new ArrayList<HashMap<String, String>>();
		ArrayList<Course>  courses=courseCatalog.getCourseCatalog();
		int size=courseCatalog.getCourseCatalog().size();
		for(int i=0;i<size;i++){
			HashMap<String, String> map=new HashMap<String, String>();
			map.put("number", courses.get(i).getNumber());
			map.put("name", courses.get(i).getName());
			map.put("credits", String.valueOf(courses.get(i).getCredits()));
			list.add(map);
		}
		return list;
	}
	@Override
	public List<HashMap<String, String>> queryPrevCourse(String number) {
		List<HashMap<String, String>> list=new ArrayList<HashMap<String, String>>();
		if(courseCatalog.getMap().get(number).getPrevCourse()!=null){
			ArrayList<Course> courses=courseCatalog.getMap().get(number).getPrevCourse();
			int size=courses.size();
			for(int i=0;i<size;i++){
				HashMap<String, String> map=new HashMap<String, String>();
				map.put("name", courses.get(i).getName());
				map.put("number", courses.get(i).getNumber());
				map.put("credits", String.valueOf(courses.get(i).getCredits()));
				list.add(map);
			}
		}
		return list;
	}
	@Override
	public boolean addCourse(Course course) {
		// TODO Auto-generated method stub
		boolean result=courseCatalog.addCourse(course);
		return result;
	}
	@Override
	public boolean deleteCourse(String number) {
		// TODO Auto-generated method stub
		boolean result=courseCatalog.deleteCourse(number);
		return result;
	}
	@Override
	public Course selectCourseByNum(String num) {
		// TODO Auto-generated method stub
		return courseCatalog.getMap().get(num);
	}

	

}
