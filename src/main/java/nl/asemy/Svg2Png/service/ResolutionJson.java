package nl.asemy.Svg2Png.service;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.NumericNode;

import nl.asemy.Svg2Png.api.query.Resolution;

@JsonComponent
public class ResolutionJson {
	public static class ResolutionDeserializer extends JsonDeserializer<Resolution> {

		@Override
		public Resolution deserialize(JsonParser p, DeserializationContext ctxt)
				throws IOException, JsonProcessingException {
			TreeNode treeNode = p.getCodec().readTree(p);

			NumericNode height = (NumericNode) treeNode.get("height");
			NumericNode width = (NumericNode) treeNode.get("width");

			return new Resolution(height.intValue(), width.intValue());
		}
	}

}