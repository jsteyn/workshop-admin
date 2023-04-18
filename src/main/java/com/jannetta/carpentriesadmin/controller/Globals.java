package com.jannetta.carpentriesadmin.controller;

import org.apache.batik.apps.rasterizer.DestinationType;
import org.apache.batik.apps.rasterizer.SVGConverter;
import org.apache.batik.apps.rasterizer.SVGConverterException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import com.jannetta.carpentriesadmin.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.Scanner;

/**
 * A singleton that can be access from anywhere
 * 
 * @author jannetta
 *
 */
public class Globals {
	private static Logger logger = LoggerFactory.getLogger(Globals.class);
	private String workshopType[] = { "swc", "dc", "lc", "ttt" };

	private static Globals globals = null;
	private static Workshops workshops = new Workshops();

	private static boolean editDetected = false;
	private static GsonBuilder gsonBuilder = new GsonBuilder();
	private static Gson gson = gsonBuilder.setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
	private static Properties properties;
	private static String propertyfile = "system.properties";
	private static String workshopfile = "Workshops.json";
	private static String learnerfile = "Learners.json";
	private static String lessonfile = "Lessons.json";
	private static String peoplefile = "People.json";
	private static String workshopLessonsfile = "WorkshopLessons.json";

	private static WorkshopTableModel workshopTableModel = new WorkshopTableModel();
	private static LearnerTableModel learnerTableModel = new LearnerTableModel();
	private static WorkshopComboBoxModel workshopComboBoxModel;
	private static LessonTableModel lessonTableModel = new LessonTableModel();
	private static PeopleTableModel peopleTableModel = new PeopleTableModel();
	private static WorkshopLessonsTableModel workshopLessonsTableModel = new WorkshopLessonsTableModel();

	private boolean workshopssaved = true;
	private boolean learnerssaved = true;
	private boolean lessonssaved = true;
	private boolean peoplesaved = true;
	private boolean workshopLessonssaved = true;

	private static Learners all_learners = new Learners();
	private static Learners learners = all_learners;
	private static Lessons lessons = new Lessons();
	private static People people = new People();
	private static WorkshopsLessonsAll workshopsAllLessons = new WorkshopsLessonsAll();



	public static Globals getInstance() {
		if (globals == null) {
			globals = new Globals();

			checkFile(propertyfile); // If the file doesn't exist create it

			properties = loadProperties();

			// Load data from files
			workshops = loadWorkshops(workshops);
			lessons = loadLessons(lessons);
			all_learners = loadLearners(learners);
			learners = all_learners;
			people = loadPeople(people);
			workshopsAllLessons = loadWorkshopsLessons(workshopsAllLessons);

			workshopComboBoxModel = new WorkshopComboBoxModel(globals.getWorkshops().getWorkshopIDs());
			workshopTableModel.setWorkshops(workshops);
			learnerTableModel.setLearners(learners);
			lessonTableModel.setLessons(lessons);
			peopleTableModel.setPeople(people);

		}
		return globals;
	}


	private static void checkFile(String filename) {
		try {
			logger.debug("Check if file " + filename + " exists.");
			File f1 = new File(filename);
			f1.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Load properties from system.properties file
	 */
	public static Properties loadProperties() {
		logger.trace("Load properties from " + propertyfile);
		properties = new Properties();
		try {
			File f = new File(propertyfile);
			// If the file doesn't exist, create it

			if (!(f.exists())) {
				OutputStream out = new FileOutputStream(f);
				logger.trace("Create system.properties file");
				out.close();
			}
			InputStream is = new FileInputStream(f);
			properties.load(is);
			// If there are no properties yet, set STORAGE to the default value of /upload
			if (properties.size() == 0) {
				logger.trace("Add basic properties");
				properties.setProperty("learnerfile", learnerfile);
				properties.setProperty("workshopfile", workshopfile);
				properties.setProperty("lessonfile", lessonfile);
				properties.setProperty("peoplefile", peoplefile);
				properties.setProperty("workshopLessonsfile", workshopLessonsfile);
			}
			FileOutputStream out = new FileOutputStream(propertyfile);
			properties.store(out, "");
			is.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * Set the specified property with the specified value
	 * 
	 * @param property The property to set
	 * @param value    The value to set the property to
	 */
	public void setProperty(String property, String value) {
		properties.setProperty(property, value);
		File f = new File("system.properties");
		try {
			OutputStream out = new FileOutputStream(f);
			properties.store(out, "This is an optional header comment string");
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Get the specified property and return its value
	 * 
	 * @param property property to return
	 * @return property
	 */
	public String getProperty(String property) {
		if (properties == null) {
			loadProperties();
		}
		return properties.getProperty(property);
	}



	public void saveJSON(String filename, Object object) {
		try {
			Writer writer = new FileWriter(filename);
			gson.toJson(object, writer);
			logger.debug("Saving " + filename);
			writer.close();
		} catch (JsonIOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public boolean isWorkshopLessonssaved() {
		return workshopLessonssaved;
	}
	public void setWorkshopLessonssaved(boolean workshopLessonssaved) {
		this.workshopLessonssaved = workshopLessonssaved;
	}

	public boolean isWorkshopssaved() {
		return workshopssaved;
	}
	public void setWorkshopssaved(boolean workshopssaved) {
		this.workshopssaved = workshopssaved;
	}

	public boolean isLearnerssaved() {
		return learnerssaved;
	}
	public void setLearnerssaved(boolean learnerssaved) {
		this.learnerssaved = learnerssaved;
	}

	public void setLessonssaved(boolean lessonssaved) {		this.lessonssaved = lessonssaved;}
	public boolean isLessonssaved() {
		return lessonssaved;
	}

	public boolean isPeoplesaved() { return peoplesaved; }
	public void setPeoplesaved(boolean peoplesaved) {this.peoplesaved = peoplesaved;}

	public String[] getWorkshopType() {
		return workshopType;
	}
	public void setWorkshopType(String[] workshopType) {
		this.workshopType = workshopType;
	}



	/*
	Get and Set Table Models
	 */

	public WorkshopLessonsTableModel getWorkshopLessonsTableModel() {
		return workshopLessonsTableModel;
	}
	public void setWorkshopLessonsTableModel(WorkshopLessonsTableModel workshopLessonsTableModel) {
		Globals.workshopLessonsTableModel = workshopLessonsTableModel;
	}
	public WorkshopTableModel getWorkshopTableModel() {
		return workshopTableModel;
	}

	public void setWorkshopTableModel(WorkshopTableModel workshopTableModel) {
		Globals.workshopTableModel = workshopTableModel;
	}

	public LearnerTableModel getLearnerTableModel() {
		return learnerTableModel;
	}

	public void setLearnerTableModel(LearnerTableModel learnerTableModel) {
		Globals.learnerTableModel = learnerTableModel;
	}

	public WorkshopComboBoxModel getWorkshopComboBoxModel() {
		return workshopComboBoxModel;
	}

	public static void setWorkshopComboBoxModel(WorkshopComboBoxModel workshopComboBoxModel) {
		Globals.workshopComboBoxModel = workshopComboBoxModel;
	}

	public PeopleTableModel getPeopleTableModel() { return peopleTableModel;}

	public void setPeopleTableModel(PeopleTableModel peopleTabelModel) {
		Globals.peopleTableModel = peopleTabelModel;
	}

	public WorkshopLessonsTableModel workshopLessonsTableModel() {return workshopLessonsTableModel;}

	/*
	Workshop lessons related methods
	 */

	/**
	 * Get all workshops and their associated lesson
	 * @return
	 */
	public static WorkshopsLessonsAll getWorkshopsLessons() {
		return workshopsAllLessons;
	}

	/**
	 * Store the workshops and all their related lessons
	 * @param workshopsAllLessons
	 */
	public static void setWorkshopsLessons(WorkshopsLessonsAll workshopsAllLessons) {
		Globals.workshopsAllLessons = workshopsAllLessons;
	}


	public static WorkshopsLessonsAll loadWorkshopsLessons(WorkshopsLessonsAll wl) {
		try {
			logger.trace("Loading " + globals.getProperty("workshopLessonsfile"));
			checkFile(globals.getProperty("workshopLessonsfile")); // If the file doesn't exist create it
			Reader reader = Files.newBufferedReader(Paths.get(globals.getProperty("workshopLessonsfile")));
			wl = gson.fromJson(reader, WorkshopsLessonsAll.class);
			if (wl == null) {
				wl = new WorkshopsLessonsAll();
			}
			logger.trace(wl.size() + " workshop records retrieved.");
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wl;
	}

	private static WorkshopsLessonsAll getWorkshopsAllLessons() {
		return workshopsAllLessons;
	}

	/*
	Workshop related methods
	 */

	public Workshops getWorkshops() {
		return workshops;
	}

	public void setWorkshops(Workshops workshops) {
		Globals.workshops = workshops;
	}
	public Workshops delWorkshops(Workshops workshops) {
		ArrayList<Workshop> delWorkshopList = new ArrayList<Workshop>();
		for (int l = 0; l < workshops.size(); l++) {
			if (workshops.get(l).isPrint()) {
				delWorkshopList.add(workshops.get(l));
			}
		}
		for (int l = 0; l < delWorkshopList.size(); l++) {
			workshops.remove(delWorkshopList.get(l));
			globals.setWorkshopssaved(false);
		}
		return workshops;
	}

	private static Workshops loadWorkshops(Workshops ws) {
		try {
			logger.trace("Loading " + globals.getProperty("workshopfile"));
			checkFile(globals.getProperty("workshopfile")); // If the file doesn't exist create it
			Reader reader = Files.newBufferedReader(Paths.get(globals.getProperty("workshopfile")));
			ws = gson.fromJson(reader, Workshops.class);
			if (ws == null) {
				ws = new Workshops();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ws;
	}

	/*
	Workshop lessons related method
	 */


	/*
	Lesson related methods
	 */

	private static Lessons loadLessons(Lessons lessons) {
		try {
			logger.trace("Loading " + globals.getProperty("lessonfile"));
			checkFile(globals.getProperty("lessonfile")); // If the file doesn't exist create it
			Reader reader = Files.newBufferedReader(Paths.get(globals.getProperty("lessonfile")));
			lessons = gson.fromJson(reader, Lessons.class);
			if (lessons == null) {
				lessons = new Lessons();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lessons;
	}


	public Lessons getLessons() {
		return lessons;
	}

	public LessonTableModel getLessonTableModel() {
		return lessonTableModel;
	}


	public Lesson findLesson(String lessonID) {
		for (int i = 0; i < lessons.size(); i++) {
			if (lessons.get(i).getLessonID().equals(lessonID)) {
				return lessons.get(i);
			}
		}
		return null;
	}

	public static void delLessons() {
		lessons.delLessons();
	}

	public static Lessons getSelectedLessons(Lessons lessons) {
		Lessons selectedLessons = new Lessons();
		for (int i = 0; i < lessons.size(); i++) {
			if (lessons.get(i).isPrint()) {
				selectedLessons.add(lessons.get(i));
			}
		}
		globals.setWorkshopLessonssaved(false);

		return selectedLessons;
	}


	/*
	People related methods
	 */

	private static People loadPeople(People people) {
		try {
			logger.trace("Loading " + globals.getProperty("peoplefile"));
			checkFile(globals.getProperty("peoplefile"));
			Reader reader = Files.newBufferedReader(Paths.get(globals.getProperty("peoplefile")));
			people = gson.fromJson(reader, People.class);
			if (people == null) {
				people = new People();
			}
			reader.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return people;
	}

	public static People delPeople(People people) {
		ArrayList<Person> delPeople = new ArrayList<Person>();
		for (int l = 0; l < people.size(); l++) {
			if (people.get(l).isPrint()) {
				delPeople.add(people.get(l));
			}
		}
		for (int l = 0; l < delPeople.size(); l++) {
			people.remove(delPeople.get(l));
			globals.setLearnerssaved(false);
		}
		return people;
	}

	public People getPeople() {
		return people;
	}
	public People getAllPeople() {
		if (people == null)
			people = new People();
		return people;
	}

	public Person findPerson(String personID) {
		for (int i = 0; i < people.size(); i++) {
			if (people.get(i).getPersonID().equals(personID)) {
				return people.get(i);
			}
		}
		return null;
	}


	/*
	Learner related methods
	 */

	private static Learners loadLearners(Learners lrns) {
		try {
			logger.trace("Loading " + globals.getProperty("learnerfile"));
			checkFile(globals.getProperty("learnerfile")); // If the file doesn't exist create it
			Reader reader = Files.newBufferedReader(Paths.get(globals.getProperty("learnerfile")));
			lrns = gson.fromJson(reader, Learners.class);
			if (lrns == null) {
				lrns = new Learners();
			}
			reader.close();
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return lrns;
	}

	public Learners getAllLearners() {
		if (all_learners == null)
			all_learners = new Learners();
		learners = all_learners;
		return all_learners;
	}

	public Learners getLearners() {
		return learners;
	}

	public void setAllLearners(Learners learners) {
		all_learners = learners;
	}

	public void learnersSetView(String view) {
		learners = new Learners();
		if (view.equals("All")) {
			learners = all_learners;
		} else {
			if (!(all_learners.getLearners() == null))
				all_learners.getLearners().forEach(learner -> {
					if (learner.getWorkshop().equals(view)) {
						learners.add(learner);
					}
				});
		}
		learnerTableModel.setLearners(learners);
		fireTableDataChanged();
	}
	public Learners delLearners(Learners learners) {
		ArrayList<Learner> delLearners = new ArrayList<Learner>();
		for (int l = 0; l < learners.size(); l++) {
			if (learners.get(l).isPrint()) {
				delLearners.add(learners.get(l));
			}
		}
		for (int l = 0; l < delLearners.size(); l++) {
			learners.remove(delLearners.get(l));
			globals.setLearnerssaved(false);
		}
		return learners;
	}

	public Learner findLearner(String userID) {
		for (int i = 0; i < all_learners.size(); i++) {
			if (all_learners.get(i).getUser_id().equals(userID)) {
				return all_learners.get(i);
			}
		}
		return null;
	}



	/**
	 * Create pdf certificate for a specific person
	 * @param certificate
	 * @param userID
	 */
	public static void svg2pdf(String certificate, String userID) {
		File outputFile;
		File f_svgFile = new File(certificate);
		try {
			outputFile = File.createTempFile("result-", ".pdf");
			SVGConverter converter = new SVGConverter();
			converter.setDestinationType(DestinationType.PDF);
			converter.setSources(new String[] { f_svgFile.toString() });
			converter.setDst(outputFile);
			converter.execute();
			Path copied = Paths.get(userID + ".pdf");
			Files.copy(outputFile.toPath(), copied);
			Files.delete(outputFile.toPath());
			Files.delete(f_svgFile.toPath());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (SVGConverterException e) {
			logger.error(e.getMessage());
		}

	}

	/*
	Text file related methods
	 */

	public Learners importTextFile(Learners learners, String workshop, String badge, String instructor, String date, File file) {
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNext()) {
				String line = sc.nextLine().strip();
				String[] tokens = line.split(",");
				date = (date.strip().equals("") ? now() : date);
				Learner learner = new Learner(workshop, badge, instructor, (tokens[0] + "_" + tokens[1]).replace(" ", "_"), tokens[0], "",
						tokens[1], tokens[2], date, new Lessons());
				learners.add(learner);
			}
			sc.close();
			fireTableDataChanged();
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		}
		return learners;
	}

	public Learners readCSVLearnersFile(Learners learners, File filename, String workshop) {
		try {
			Scanner sc = new Scanner(filename);
			while (sc.hasNext()) {
				String line = sc.nextLine();
				logger.debug("Read line: " + line);
				String[] tokens = line.split(",");
				// workshop, instructor, user_id, name, email, date
				Learner learner = new Learner(workshop, tokens[0].split("-")[0], tokens[1], tokens[2], tokens[3], "",
						"", tokens[4], tokens[5], new Lessons());
				learners.add(learner);
			}
			sc.close();
			fireTableDataChanged();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return learners;
	}

	/*
	Helper methods
	 */

	public static String now() {
		String DATE_FORMAT_NOW = "dd MMM yyyy";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		return sdf.format(cal.getTime());
	}

	public static boolean isEditDetected() {
		return editDetected;
	}

	public static void setEditDetected(boolean editDetected) {
		Globals.editDetected = editDetected;
	}


	public void fireTableDataChanged() {
		workshopTableModel.fireTableDataChanged();
		logger.trace("Update workshop table model");
		learnerTableModel.fireTableDataChanged();
		logger.trace("Update learner table model");
		lessonTableModel.fireTableDataChanged();
		logger.trace("Update lesson table model");
		peopleTableModel.fireTableDataChanged();
		logger.trace("Update people table model");
	}

	public static ImageIcon createImageIcon(String path, String description) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		try {
			Image icon = toolkit.getImage(ClassLoader.getSystemResource(path));
			logger.trace(path + " found.");
			return new ImageIcon(icon);
		} catch (NullPointerException e) {
			logger.error(path + " not found.");
			return null;
		}
	}


}
