package cubahomes.services.aws;

//@Service
public class FileStoreService {

//	@Autowired
//	private AmazonS3 s3;
//
//	public void saveFile(String path, String fileName, InputStream input,
//			Optional<Map<String, String>> optionalMetaData) {
//
//		ObjectMetadata metaData = new ObjectMetadata();
//		optionalMetaData.ifPresent(map -> {
//			map.forEach(metaData::addUserMetadata);
//		});
//		
//		try {
//		s3.putObject(path, fileName, input, metaData);
//		}catch(AmazonServiceException e) {
//			throw new IllegalStateException("No se pudo guardar el archivo", e);
//		}
//	}
}
