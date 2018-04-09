package nl.asemy.Svg2Png.api.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.apache.batik.transcoder.TranscoderException;
import org.python.bouncycastle.util.encoders.Base64;
import org.python.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import nl.asemy.Svg2Png.api.query.Resolution;
import nl.asemy.Svg2Png.service.SvgToPngConveter;

@RestController
public class UtilController {

	@Autowired
	private SvgToPngConveter converter;

	@RequestMapping(value = "/util/convert")
	@ResponseBody
	public ResponseEntity<Map<Resolution, String>>

			convertToResolutions(@RequestPart(name = "file", required = true) @NotNull final MultipartFile mpf,
					@RequestPart(name = "targetResolutions", required = false) Set<Resolution> targetResolutions)
					throws IOException, TranscoderException {

		if (targetResolutions == null || targetResolutions.size() == 0)
			targetResolutions = Sets.newHashSet(new Resolution(100, 100));

		HashMap<Resolution, String> convert = converter.convert(mpf.getBytes(), targetResolutions);

		return ResponseEntity.ok().body(convert);
	}

	@PostMapping(value = "/util/getbytes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public byte[] getBytes(@RequestPart(name = "file", required = true) final MultipartFile mpf) throws IOException {
		return Base64.encode(mpf.getBytes());
	}

	@PostMapping(value = "/util/base64_to_image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<byte[]> base64ToImage(@RequestParam String base64) throws IOException {

		return ResponseEntity.ok().body(Base64.decode(base64));
	}
}
