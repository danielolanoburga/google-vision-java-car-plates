package com.upc.ia.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;

@Service
public class GoogleVisionService {

	public String processImage(MultipartFile file) throws IOException {

		try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {

			// The path to the image file to annotate
			// String fileName = "./resources/wakeupcat.jpg";
			//String fileName = file.getOriginalFilename();

			// Reads the image file into memory
			// Path path = Paths.get(fileName);
			// byte[] data = Files.readAllBytes(path);
			byte[] data = file.getBytes();
			ByteString imgBytes = ByteString.copyFrom(data);

			// Builds the image annotation request
			List<AnnotateImageRequest> requests = new ArrayList<>();
			Image img = Image.newBuilder().setContent(imgBytes).build();
			Feature feat = Feature.newBuilder().setType(Type.LABEL_DETECTION).build();
			Feature feat2 = Feature.newBuilder().setType(Type.TEXT_DETECTION).build();
			//AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat2).setImage(img).build();
			AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).addFeatures(feat2).setImage(img).build();
			requests.add(request);

			// Performs label detection on the image file
			BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
			List<AnnotateImageResponse> responses = response.getResponsesList();

			for (AnnotateImageResponse res : responses) {
				if (res.hasError()) {
					System.out.format("Error: %s%n", res.getError().getMessage());
					return "Error: %s%n : " + res.getError().getMessage();
				}

				System.out.println("############# LABELS OF IMAGE #############");
				for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
					annotation.getAllFields().forEach((k, v) -> System.out.format("%s : %s%n", k, v.toString()));
				}
				System.out.println("############# TEXT OF IMAGE #############");
				for (EntityAnnotation annotation : res.getTextAnnotationsList()) {
					annotation.getAllFields().forEach((k, v) -> System.out.format("%s : %s%n", k, v.toString()));
				}
			}
		}

		return "";
	}

}
