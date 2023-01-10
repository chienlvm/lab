package chienlvm.fsoft.vn.dto.request;

import java.util.List;

public class TopPapeRequest {

	private List<String> category;
	private int recommendAuthor;
	private int recommendTypeBook;
	private int recommendTop;


	public List<String> getCategory() {
		return category;
	}

	public void setCategory(List<String> category) {
		this.category = category;
	}

	public int getRecommendAuthor() {
		return recommendAuthor;
	}

	public void setRecommendAuthor(int recommendAuthor) {
		this.recommendAuthor = recommendAuthor;
	}

	public int getRecommendTypeBook() {
		return recommendTypeBook;
	}

	public void setRecommendTypeBook(int recommendTypeBook) {
		this.recommendTypeBook = recommendTypeBook;
	}

	public int getRecommendTop() {
		return recommendTop;
	}

	public void setRecommendTop(int recommendTop) {
		this.recommendTop = recommendTop;
	}

}
