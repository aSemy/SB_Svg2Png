package nl.asemy.Svg2Png.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.python.apache.xerces.impl.dv.util.Base64;
import org.springframework.stereotype.Service;

import nl.asemy.Svg2Png.api.query.Resolution;

@Service
public class SvgToPngConveter {

	public HashMap<Resolution, String> convert(final byte[] svgBytes, Set<Resolution> targetResolutions)
			throws IOException, TranscoderException {

		HashMap<Resolution, String> resolutionsToPNG_Base64EncodedBytes = new HashMap<>();

		// create a PNG transcoder
		PNGTranscoder t = new PNGTranscoder();

		for (final Resolution resolution : targetResolutions) {

			// Set the transcoding hints
			t.addTranscodingHint(PNGTranscoder.KEY_MAX_HEIGHT, Float.valueOf(resolution.getHeight()));
			t.addTranscodingHint(PNGTranscoder.KEY_MAX_WIDTH, Float.valueOf(resolution.getWidth()));

			// Create the transcoder input
			InputStream is = new ByteArrayInputStream(svgBytes);
			TranscoderInput input = new TranscoderInput(is);

			// Create the transcoder output
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			TranscoderOutput output = new TranscoderOutput(bos);

			// Save the image
			t.transcode(input, output);

			byte[] pngBytes = bos.toByteArray();
			resolutionsToPNG_Base64EncodedBytes.put(resolution, Base64.encode(pngBytes));

			// Flush and close streams
			is.close();
			bos.flush();
			bos.close();
		}

		return resolutionsToPNG_Base64EncodedBytes;
	}
}
