package sample;

import java.util.ArrayList;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/*
main class of S2Dao
*/
public class S2Dao_sample10 {
	// path of sample file
	private static final String PATH = "sample/s2Dao.dicon";
	/*
	main class
	@param args parameter
	*/
	public static void main(String[] args){
		System.out.println("S2Dao_sample10 start");
		
		// 1.to read a setting file
		SingletonS2ContainerFactory.setConfigPath(PATH);
		
		// 2.to initiate
		SingletonS2ContainerFactory.init();
		
		// 3.to get a container
		S2Container container = SingletonS2ContainerFactory.getContainer();
		
		// 4.to call the component
		ProjectDao pjDao = (ProjectDao) container.getComponent(ProjectDao.class);
		
		// 5.to execute the method (select * from project)
		// ArrayList<Project> list = pjDao.selectProject();
		// ArrayList<Project> list = pjDao.selectWhereProject("2", "3");
		// ArrayList<Project> list = pjDao.selectUseSqlFileProject(1, 3);
		
/*		int startId = 2;
		int endId = 4;
		String pjName = "SqlFile";
		ArrayList<Project> list = pjDao.selectUseSqlFileProject2(
				startId, endId, pjName);*/
		
/*		String orderByKey = "id";
		String sortKey = "desc";
		ArrayList<Project> list = pjDao.selectUseSqlFileProject3(
				orderByKey, sortKey);*/
		
		//ArrayList<Project> list = pjDao.selectUseSqlFileProject4("S2Dao");
		//ArrayList<Project> list = pjDao.selectUseSqlFileProject4(null);
		
		//ArrayList<Project> list = pjDao.selectUseSqlFileProject5("S2Dao");
		//ArrayList<Project> list = pjDao.selectUseSqlFileProject5(null);
		
/*		for (Project project : list) {
			System.out.print("project.id: " + project.id);
			System.out.println(": project.name: " + project.pjName);
		}*/
		
/*		Project pj = new Project();
		pj.id = 5;
		pj.pjName = "@Arguments";
		//pjDao.insertProject(pj);
		//pjDao.updateProject(pj);
		pjDao.deleteProject(pj);*/
		ArrayList<Project> listAfter = pjDao.selectProject();
		for (Project project : listAfter) {
			System.out.print("project.id: " + project.id);
			System.out.println(", project.name: " + project.pjName);
		}
	}
}
