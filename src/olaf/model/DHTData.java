package olaf.model;

import java.util.*;

public class DHTData {
	private float temp;
	private float humd;
	private long timeStamp;
	
	public DHTData() {
		
	}

	public float getHumd() {
		return humd;
	}

	public void setHumd(float humd) {
		this.humd = humd;
	}

	public float getTemp() {
		return temp;
	}

	public void setTemp(float temp) {
		this.temp = temp;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStampSec) {
		this.timeStamp = timeStampSec;
	}
	
	
	public List<String> toList() {
		ArrayList<String> result = new ArrayList<String>();
		result.add(String.valueOf(getTemp()));
		result.add(String.valueOf(getHumd()));
		result.add(String.valueOf(getTimeStamp()));
		return result;
	}

}
