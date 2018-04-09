package nl.asemy.Svg2Png.api.query;

import java.util.Set;

public class SvgConvertArgument {

	private byte[] bytes;

	private Set<Resolution> targetResolutions;

	public void setBytes(final byte[] bytes) {
		this.bytes = bytes;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public Set<Resolution> getTargetResolutions() {
		return targetResolutions;
	}

	public void setTargetResolutions(Set<Resolution> targetResolutions) {
		this.targetResolutions = targetResolutions;
	}
}