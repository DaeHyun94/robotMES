package com.robotMES.comm;

public class FrontUser implements CommControllerInterface{

	@Override
	public void execute() {
		boolean isStop = false;
		do {
			menuDisplay();
			int job = sc.nextInt();
			
			switch(job) {
				case 1->{f_managementByStation();}
				case 2->{f_managementByRobot();}
				case 3->{f_managementByProduct();}
				case 4->{f_managementByOrder();}
				//case 5->{f_managementByAdmin();}
				//case 6->{f_managementByUser();}
				case 0->{logoutDisplay();; isStop = true; continue;}
				default ->{continue;}
			}
			
		}while(!isStop);
		
	}

}
