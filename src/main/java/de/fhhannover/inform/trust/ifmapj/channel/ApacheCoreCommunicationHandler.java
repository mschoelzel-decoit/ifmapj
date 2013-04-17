package de.fhhannover.inform.trust.ifmapj.channel;

/*
 * #%L
 * ====================================================
 *   _____                _     ____  _____ _   _ _   _
 *  |_   _|_ __ _   _ ___| |_  / __ \|  ___| | | | | | |
 *    | | | '__| | | / __| __|/ / _` | |_  | |_| | |_| |
 *    | | | |  | |_| \__ \ |_| | (_| |  _| |  _  |  _  |
 *    |_| |_|   \__,_|___/\__|\ \__,_|_|   |_| |_|_| |_|
 *                             \____/
 * 
 * =====================================================
 * 
 * Fachhochschule Hannover 
 * (University of Applied Sciences and Arts, Hannover)
 * Faculty IV, Dept. of Computer Science
 * Ricklinger Stadtweg 118, 30459 Hannover, Germany
 * 
 * Email: trust@f4-i.fh-hannover.de
 * Website: http://trust.inform.fh-hannover.de/
 * 
 * This file is part of IfmapJ, version 0.1.5, implemented by the Trust@FHH 
 * research group at the Fachhochschule Hannover.
 * 
 * IfmapJ is a lightweight, platform-independent, easy-to-use IF-MAP client
 * library for Java. IF-MAP is an XML based protocol for sharing data across
 * arbitrary components, specified by the Trusted Computing Group. IfmapJ is
 * maintained by the Trust@FHH group at the Fachhochschule Hannover. IfmapJ 
 * was developed within the ESUKOM research project.
 * %%
 * Copyright (C) 2010 - 2013 Trust@FHH
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.IOException;
import java.io.InputStream;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.params.BasicHttpParams;

import de.fhhannover.inform.trust.ifmapj.exception.InitializationException;

/**
 * Implementation of the {@link CommunicationHandler} interface using
 * Apache's httpcomponent-core package only.
 * 
 * @author aw
 *
 */
class ApacheCoreCommunicationHandler extends AbstractCommunicationHandler {
	
	private final BasicHttpParams mBasicHttpParams;
	private DefaultHttpClientConnection mHttpConnection;
	private boolean mResponseGzip;
	private BasicHttpEntityEnclosingRequest mHttpPost;
	
	ApacheCoreCommunicationHandler(String url, String user, String pass,
			SSLSocketFactory sslSocketFactory, HostnameVerifier verifier)
			throws InitializationException {
		super(url, user, pass, sslSocketFactory, verifier);
		
		mBasicHttpParams = new BasicHttpParams();
	}

	@Override
	public InputStream doActualRequest(InputStream is) throws IOException {
		InputStreamEntity ise = null;
		HttpResponse response = null;
		InputStream ret = null;
		Header hdr = null;
		HttpEntity respEntity = null;
		StatusLine status = null;
		
		
		ise = new InputStreamEntity(is, is.available());
		ise.setChunked(false);
		mHttpPost.setEntity(ise);
		
		// do the actual request
		try {
			mHttpConnection.sendRequestHeader(mHttpPost);
			mHttpConnection.sendRequestEntity(mHttpPost);
			response = mHttpConnection.receiveResponseHeader();
			mHttpConnection.receiveResponseEntity(response);
		} catch (HttpException e) {
			throw new IOException(e);
		}

		// check if we got a 200 status back, otherwise go crazy
		status = response.getStatusLine();
		if (status.getStatusCode() != 200) {
			throw new IOException("HTTP Status Code: "
					+ status.getStatusCode() + " " 
					+ status.getReasonPhrase());
		}
		
		// check whether the response uses gzip
		hdr = response.getFirstHeader("Content-Encoding");
		mResponseGzip = (hdr == null) ? false : hdr.getValue().contains("gzip");
		respEntity = response.getEntity();
		
		if (respEntity != null)
			ret = respEntity.getContent();
		
		if (ret == null)
			throw new IOException("no content in response");
		
		return ret;
	}
	
	@Override
	protected void prepareCommunication() throws IOException {
		if (mHttpConnection == null) {
			mHttpConnection = new DefaultHttpClientConnection();
			mHttpConnection.bind(getSocket(), mBasicHttpParams);
		}
	}

	@Override
	protected void createPostRequest(String path) throws IOException {
		mHttpPost = new BasicHttpEntityEnclosingRequest("POST", path);
	}

	@Override
	protected void addHeader(String key, String value) throws IOException {
		mHttpPost.addHeader(key, value);
	}

	@Override
	protected void finishHeaders() throws IOException {
		// noop, we do this in doActualRequest()
	}

	@Override
	protected boolean replyIsGzipped() throws IOException {
		return mResponseGzip;
	}
	
	@Override
	public void closeTcpConnectionImpl() throws IOException {
	
		IOException tmp = null;
		try {
			if (mHttpConnection != null) 
				mHttpConnection.close();
		} catch (IOException e) {
			tmp = e;
		} finally {
			mHttpConnection = null;
		}
		
		if (tmp != null)
			throw tmp;
	}
}
