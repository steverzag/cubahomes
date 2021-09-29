package cubahomes.config;

public enum BucketName {
	
	ANOUNCE_IMAGE("");
	
	private final String bucketName;

	private BucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	
	public String getBucketName() {
		return bucketName;
	}
}
