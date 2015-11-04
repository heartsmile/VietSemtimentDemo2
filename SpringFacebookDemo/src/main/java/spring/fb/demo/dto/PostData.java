/**
 * 
 */
package spring.fb.demo.dto;

import org.springframework.stereotype.Component;

/**
 * @author AnhQuan
 *
 */
@Component
public class PostData {
	private String message;
	private String description;
	private String story;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	
}
