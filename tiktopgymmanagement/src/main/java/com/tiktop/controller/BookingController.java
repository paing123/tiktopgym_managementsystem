package com.tiktop.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tiktop.model.Booking;
import com.tiktop.model.Member;
import com.tiktop.model.Package;
import com.tiktop.model.Schedule;
import com.tiktop.model.Trainer;
import com.tiktop.services.BookingService;
import com.tiktop.services.MemberService;
import com.tiktop.services.PackageService;
import com.tiktop.services.ScheduleService;
import com.tiktop.services.TrainerService;

@Controller
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private TrainerService trainerService;
	
	@Autowired
	private PackageService packageService;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@InitBinder
	private void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	@RequestMapping(value = { "/bookingSchedule" }, method = RequestMethod.GET)
	public String memberBookingSchedule(Model model,HttpSession session) {
		List<Schedule> scheduleDates = bookingService.findScheduleByDate(new Schedule());
		String login = (String) session.getAttribute("currentUser");
		model.addAttribute("login",login);
		int i = 1;
		for (Schedule schedule : scheduleDates) {
			String date = schedule.getCreateDate();
			Schedule scheduleWithDate = new Schedule();
			scheduleWithDate.setCreateDate(date);
			List<Schedule> schedules = scheduleService.findSchedule(scheduleWithDate);
			model.addAttribute("schedule" + i, schedules);
			i++;
		}
		model.addAttribute("scheduleDates", scheduleDates);
		return "bookingSchedule";
	}
	
	@RequestMapping(value = { "/bookingPackage" }, method = RequestMethod.GET)
	public String memberBookingPackage(Model model,HttpSession session) {
		String login = (String) session.getAttribute("currentUser");
		model.addAttribute("login",login);
		List<Package> packs = packageService.findPackage(new Package());
		model.addAttribute("packs",packs);
		return "bookingPackage";
	}
	
	@RequestMapping(value = { "/bookingTrainer" }, method = RequestMethod.GET)
	public String memberBookingTrainer(Model model,HttpSession session) {
		String login = (String) session.getAttribute("currentUser");
		model.addAttribute("login",login);
		List<Trainer> trainers = trainerService.findTrainer(new Trainer());
		model.addAttribute("trainers",trainers);
		return "bookingTrainer";
	}
	
	@GetMapping("/member/schedulebookingdetail/{id}")
	public ModelAndView scheduleBookingDetail(@ModelAttribute("id") Integer id,HttpSession session) {
		ModelAndView mav = new ModelAndView("member/schedulebookingdetail");
		Schedule schedule = new Schedule();
		schedule.setScheduleId(id);
		List<Schedule> schedules=bookingService.scheduleBookingDetail(schedule);
		schedule = schedules.get(0);
		mav.addObject("schedule", schedule);
		String currentUser = (String)session.getAttribute("currentUser");
		Member member = new Member();
		member.setLogin(currentUser);
		List<Member> members = memberService.findMember(member);
		member = members.get(0);
		mav.addObject("member",member);
		Booking booking = new Booking();
		mav.addObject("booking",booking);
		return mav;
	}
	
	@GetMapping("/member/packagebookingdetail/{id}")
	public ModelAndView packageBookingDetail(@ModelAttribute("id") Integer id,HttpSession session) {
		ModelAndView mav = new ModelAndView("member/packagebookingdetail");
		Package pack = new Package();
		pack.setPackageId(id);
		List<Package> packs=packageService.findPackage(pack);
		pack = packs.get(0);
		LocalDate expiredDate = bookingService.checkExpiredDate(pack.getPackageDuration());
		mav.addObject("expiredDate",expiredDate);
		mav.addObject("pack", pack);
		String currentUser = (String)session.getAttribute("currentUser");
		Member member = new Member();
		member.setLogin(currentUser);
		List<Member> members = memberService.findMember(member);
		member = members.get(0);
		mav.addObject("member",member);
		Booking booking = new Booking();
		mav.addObject("booking",booking);
		return mav;
	}
	
	@GetMapping("/member/trainerbookingdetail/{id}")
	public ModelAndView trainerBookingDetail(@ModelAttribute("id") Integer id,HttpSession session) {
		ModelAndView mav = new ModelAndView("member/trainerbookingdetail");
		Trainer trainer = new Trainer();
		trainer.setTrainerId(id);
		List<Trainer> trainers=trainerService.findTrainer(trainer);
		trainer = trainers.get(0);
		LocalDate expiredDate = bookingService.checkExpiredDate(1);
		mav.addObject("expiredDate",expiredDate);
		mav.addObject("trainer", trainer);
		String currentUser = (String)session.getAttribute("currentUser");
		Member member = new Member();
		member.setLogin(currentUser);
		List<Member> members = memberService.findMember(member);
		member = members.get(0);
		mav.addObject("member",member);
		Booking booking = new Booking();
		mav.addObject("booking",booking);
		return mav;
	}
	
	@PostMapping("/member/savePackageBooking")
	public ModelAndView confirmPackageBooking(@ModelAttribute("booking") Booking booking,HttpSession session){
		Package pack = new Package();
		pack.setPackageId(booking.getPackageId());
		List<Package> packs = packageService.findPackage(pack);
		LocalDate expiredDate =  bookingService.checkExpiredDate(packs.get(0).getPackageDuration());
		booking.setExpiredDate(expiredDate.toString());
		bookingService.save(booking);
		List<Booking> bookings = bookingService.findAllBooking(new Booking());
		int bookingId = bookings.get(bookings.size()-1).getBookingId();
		session.setAttribute("bookingId",bookingId);
		ModelAndView mav = new ModelAndView("redirect:/member/paymentinfo");
		return mav;
	}
	
	@PostMapping("/member/saveScheduleBooking")
	public ModelAndView confirmScheduleBooking(@ModelAttribute("booking") Booking booking,HttpSession session){
		Schedule schedule = new Schedule();
		schedule.setScheduleId(booking.getScheduleId());
		List<Schedule> schedules = scheduleService.findSchedule(schedule);
		String expireDate =schedules.get(0).getCreateDate();
		booking.setExpiredDate(expireDate);
		bookingService.save(booking);
		List<Booking> bookings = bookingService.findAllBooking(new Booking());
		int bookingId = bookings.get(bookings.size()-1).getBookingId();
		session.setAttribute("bookingId",bookingId);
		ModelAndView mav = new ModelAndView("redirect:/member/paymentinfo");
		return mav;
	}
	
	@PostMapping("/member/saveTrainerBooking")
	public ModelAndView confirmTrainerBooking(@ModelAttribute("booking") Booking booking,HttpSession session){
		LocalDate expiredDate =  bookingService.checkExpiredDate(1);
		booking.setExpiredDate(expiredDate.toString());
		bookingService.save(booking);
		List<Booking> bookings = bookingService.findAllBooking(new Booking());
		int bookingId = bookings.get(bookings.size()-1).getBookingId();
		session.setAttribute("bookingId",bookingId);
		ModelAndView mav = new ModelAndView("redirect:/member/paymentinfo");
		return mav;
	}
	
	@GetMapping("/member/memberBookings")
	public ModelAndView selectMemberBookings(HttpSession session) {
		ModelAndView mav = new ModelAndView("member/memberBookings");
		String login = (String)session.getAttribute("currentUser");
		Booking memberBooking = new Booking();
		memberBooking.setLogin(login);
		List<Booking> packageBookings = bookingService.findPackageBooking(memberBooking);
		List<Booking> scheduleBookings = bookingService.findScheduleBooking(memberBooking);
		List<Booking> trainerBookings = bookingService.findTrainerBooking(memberBooking);
		mav.addObject("packageBookings",packageBookings);
		mav.addObject("scheduleBookings",scheduleBookings);
		mav.addObject("trainerBookings",trainerBookings);
		return mav;
	}
	
	@RequestMapping(value = { "/admin/scheduleBooking" }, method = RequestMethod.GET)
	public String bookingSchedule(Model model) {
		Booking booking = new Booking();
		model.addAttribute("booking", booking);
		return "admin/scheduleBooking";
	}
	
	@RequestMapping(value = { "/admin/searchScheduleBooking" }, method = RequestMethod.POST)
	public String searchScheduleBooking(@ModelAttribute("booking") Booking booking, Model model) {
		List<Booking> bookings = bookingService.findScheduleBooking(booking);
		model.addAttribute("bookings", bookings);
		return "admin/scheduleBooking";
	}
	
	@GetMapping("/admin/deleteScheduleBooking/{id}")
	public ModelAndView deleteScheduleBooking(@ModelAttribute("id") Integer id) {
		bookingService.delete(id);
		ModelAndView mav = new ModelAndView("admin/scheduleBooking");
		Booking booking = new Booking();
		List<Booking> bookings = bookingService.findScheduleBooking(booking);
		mav.addObject("bookings",bookings);
		mav.addObject("booking",booking);
		return mav;
	}
	
	@GetMapping("/admin/confirmScheduleBooking/{id}")
	public ModelAndView confirmBookingSchedule(@ModelAttribute("id") int id) {
		Booking booking = new Booking();
		booking.setBookingId(id);
		bookingService.confirmBooking(booking);
		List<Booking> bookings= bookingService.findScheduleBooking(new Booking());
		ModelAndView mav = new ModelAndView("admin/scheduleBooking");
		mav.addObject("bookings", bookings);
		mav.addObject("booking",booking);
		return mav;
	}
	
	@RequestMapping(value = { "/admin/packageBooking" }, method = RequestMethod.GET)
	public String bookingPackage(Model model) {
		Booking booking = new Booking();
		model.addAttribute("booking", booking);
		return "admin/packageBooking";
	}
	
	@RequestMapping(value = { "/admin/searchPackageBooking" }, method = RequestMethod.POST)
	public String searchPackageBooking(@ModelAttribute("booking") Booking booking, Model model) {
		List<Booking> bookings = bookingService.findPackageBooking(booking);
		model.addAttribute("bookings", bookings);
		return "admin/packageBooking";
	}
	
	@GetMapping("/admin/deletePackageBooking/{id}")
	public ModelAndView deletePackageBooking(@ModelAttribute("id") Integer id) {
		bookingService.delete(id);
		ModelAndView mav = new ModelAndView("admin/packageBooking");
		Booking booking = new Booking();
		List<Booking> bookings = bookingService.findPackageBooking(booking);
		mav.addObject("bookings",bookings);
		mav.addObject("booking",booking);
		return mav;
	}
	
	@GetMapping("/admin/confirmPackageBooking/{id}")
	public ModelAndView confirmPackageBooking(@ModelAttribute("id") int id) {
		Booking booking = new Booking();
		booking.setBookingId(id);
		bookingService.confirmBooking(booking);
		List<Booking> bookings= bookingService.findPackageBooking(new Booking());
		ModelAndView mav = new ModelAndView("admin/trainerBooking");
		mav.addObject("bookings", bookings);
		mav.addObject("booking",booking);
		return mav;
	}
	
	@RequestMapping(value = { "/admin/trainerBooking" }, method = RequestMethod.GET)
	public String bookingTrainer(Model model) {
		Booking booking = new Booking();
		model.addAttribute("booking", booking);
		return "admin/trainerBooking";
	}
	
	@RequestMapping(value = { "/admin/searchTrainerBooking" }, method = RequestMethod.POST)
	public String searchTrainerBooking(@ModelAttribute("booking") Booking booking, Model model) {
		List<Booking> bookings = bookingService.findTrainerBooking(booking);
		model.addAttribute("bookings", bookings);
		return "admin/trainerBooking";
	}
	
	@GetMapping("/admin/deleteTrainerBooking/{id}")
	public ModelAndView deleteTrainerBooking(@ModelAttribute("id") Integer id) {
		bookingService.delete(id);
		ModelAndView mav = new ModelAndView("admin/trainerBooking");
		Booking booking = new Booking();
		List<Booking> bookings = bookingService.findTrainerBooking(booking);
		mav.addObject("bookings",bookings);
		mav.addObject("booking",booking);
		return mav;
	}
	
	@GetMapping("/admin/confirmTrainerBooking/{id}")
	public ModelAndView confirmTrainerBooking(@ModelAttribute("id") int id) {
		Booking booking = new Booking();
		booking.setBookingId(id);
		bookingService.confirmBooking(booking);
		List<Booking> bookings= bookingService.findTrainerBooking(new Booking());
		ModelAndView mav = new ModelAndView("admin/packageBooking");
		mav.addObject("bookings", bookings);
		mav.addObject("booking",booking);
		return mav;
	}
	
}
