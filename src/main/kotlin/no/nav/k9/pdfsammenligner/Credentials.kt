package no.nav.k9.pdfsammenligner

import com.amazonaws.auth.AWSCredentials
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class AwsProperties(@Value("s3.access-key") val awsAccessKey: String,
                    @Value("s3.secret") val awsSecret: String,
                    @Value("s3.bucket") val bucket: String) : AWSCredentials
{
    override fun getAWSAccessKeyId(): String {
        return awsAccessKey
    }

    override fun getAWSSecretKey(): String {
        return awsSecret
    }
}