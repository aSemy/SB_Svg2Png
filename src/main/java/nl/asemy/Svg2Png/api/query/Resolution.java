package nl.asemy.Svg2Png.api.query;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude
@JsonSerialize
public class Resolution {

	private int height;
	private int width;

	public Resolution() {
	}

	public Resolution(int height, int width) {
		setHeight(height);
		setWidth(width);
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		Assert.isTrue(height > 0, "Height must be greater than zero");
		this.height = height;
	}

	public void setWidth(int width) {
		Assert.isTrue(width > 0, "Width must be greater than zero");
		this.width = width;
	}

	@Override
	public String toString() {
		return "width:" + width + ",height:" + height;
	}
}
