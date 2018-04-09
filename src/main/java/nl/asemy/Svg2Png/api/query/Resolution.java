package nl.asemy.Svg2Png.api.query;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude
@JsonSerialize
public class Resolution {

	@JsonProperty("height")
	private int height;
	@JsonProperty("width")
	private int width;

	public Resolution() {
	}

	@JsonCreator
	public Resolution(@JsonProperty("height") int height, @JsonProperty("width") int width) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Resolution other = (Resolution) obj;
		if (height != other.height)
			return false;
		if (width != other.width)
			return false;
		return true;
	}
}
