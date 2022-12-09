package listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

public class RetryFailedTest implements IRetryAnalyzer, org.testng.internal.annotations.IAnnotationTransformer {

	static int retryCount = 0;
	int maxCount = 2;

	@Override
	public boolean retry(ITestResult result) {
		if (result.getStatus() == result.FAILURE) {
			if (retryCount <= maxCount) {
				retryCount++;
				return true;
			}
		}
		return false;
	}
	
	public void transform(ITestAnnotation testAnnotation, Class testClass, Constructor testConstructor, Method testMethod,
			Class<?> occurringClazz) {
		testAnnotation.setRetryAnalyzer(RetryFailedTest.class);
	}


}