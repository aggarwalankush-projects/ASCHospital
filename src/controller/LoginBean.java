package controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import model.Login;


public class LoginBean {
	private static Logger logger = LogManager.getLogger(LoginBean.class
			.getName());
	private static final String PERSISTENCE_UNIT_NAME = "ASCHospital";
	private static EntityManagerFactory factory;

	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String validate() {
		String flag = "failure";
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		try {
			Login login = em.find(Login.class, userName);

			if (password.equals(login.getPassword())) {
				flag = "success";
			} else {
				logger.debug("hell0");
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"The username or password you entered is incorrect.",
						"No login because username or passsword are incorrect etc");
				FacesContext fc = FacesContext.getCurrentInstance();
				fc.addMessage("loginError", facesMsg);
				return null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			FacesMessage facesMsg = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					"The username or password you entered is incorrect.",
					"No login because username or passsword are incorrect etc");
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.addMessage("loginError", facesMsg);
			return null;
		}

		return flag;
	}
}