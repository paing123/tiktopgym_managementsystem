package com.tiktop.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.tiktop.dao.AttendanceDao;
import com.tiktop.model.Attendance;

@Service("attendanceService")
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	AttendanceDao attendanceDao;

	@Override
	public void save(Attendance attendance) {
		attendance = convertDateFormat(attendance);
		attendanceDao.save(attendance);
	}

	@Override
	public List<Attendance> findAttendance(Attendance attendance) {
		return attendanceDao.findAttendance(attendance);
	}

	@Override
	public void delete(Integer id) {
		attendanceDao.delete(id);
	}

	@Override
	public void update(Attendance attendance) {
		attendanceDao.update(attendance);
	}

	@Override
	public List<Attendance> checkAttendance(Attendance attendance) {
		return attendanceDao.checkAttendance(attendance);
	}

	@Override
	public Attendance bodyMeasurementCheck(List<Attendance> attendances) {
		Attendance startAtt = attendances.get(0);
		Attendance endAtt = attendances.get(attendances.size() - 1);
		Double startHeight = startAtt.getHeight();
		Double endHeight = endAtt.getHeight();
		Double startWeight = startAtt.getWeight();
		Double endWeight = endAtt.getWeight();
		Double resultHeight = endHeight - startHeight;
		Double resultWeight = endWeight - startWeight;
		Attendance attendance = new Attendance();
		attendance.setResultHeight(Math.round(resultHeight * 100.0) / 100.0);
		attendance.setResultWeight(Math.round(resultWeight * 100.0) / 100.0);
		return attendance;
	}

	@Override
	public ModelAndView checkLossGain(Attendance attendance) {
		ModelAndView mav = new ModelAndView("member/memberbodycheck");
		Double height = attendance.getResultHeight();
		Double weight = attendance.getResultWeight();
		if (height > 0) {
			mav.addObject("height", "increaseHeight");
		}
		else {
			mav.addObject("height", "decreaseHeight");
		}
		if (weight > 0) {
			mav.addObject("weight", "gainWeight");
		}
		else {
			mav.addObject("weight", "lossWeight");
		}
		attendance.setResultHeight(Math.abs(height));
		attendance.setResultWeight(Math.abs(weight));
		mav.addObject("attendance", attendance);
		return mav;
	}

	@Override
	public Boolean checkDate(Attendance attendance) throws ParseException {
		String startDate = attendance.getStartDate();
		String endDate = attendance.getEndDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date sDate = sdf.parse(startDate);
		Date eDate = sdf.parse(endDate);
		if (eDate.compareTo(sDate) >= 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Attendance> findAttendanceMemberId(Attendance attendance) {
		return attendanceDao.findAttendanceMemberId(attendance);
	}

	@Override
	public Attendance convertDateFormat(Attendance attendance) {
		String date = attendance.getAttendanceDate();
		String dates[]=date.split("/");
		Collections.reverse(Arrays.asList(dates)); 
		String newDate = "";
		for (String str : dates) {
			newDate=newDate+"-"+str;
		}
		attendance.setAttendanceDate(newDate.substring(1));
		return attendance;
	}
}
