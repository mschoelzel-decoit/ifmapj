package de.hshannover.f4.trust.ifmapj.identifier;

/*
 * #%L
 * =====================================================
 *   _____                _     ____  _   _       _   _
 *  |_   _|_ __ _   _ ___| |_  / __ \| | | | ___ | | | |
 *    | | | '__| | | / __| __|/ / _` | |_| |/ __|| |_| |
 *    | | | |  | |_| \__ \ |_| | (_| |  _  |\__ \|  _  |
 *    |_| |_|   \__,_|___/\__|\ \__,_|_| |_||___/|_| |_|
 *                             \____/
 * 
 * =====================================================
 * 
 * Hochschule Hannover
 * (University of Applied Sciences and Arts, Hannover)
 * Faculty IV, Dept. of Computer Science
 * Ricklinger Stadtweg 118, 30459 Hannover, Germany
 * 
 * Email: trust@f4-i.fh-hannover.de
 * Website: http://trust.f4.hs-hannover.de
 * 
 * This file is part of IfmapJ, version 1.0.0, implemented by the Trust@HsH
 * research group at the Hochschule Hannover.
 * 
 * IfmapJ is a lightweight, platform-independent, easy-to-use IF-MAP client
 * library for Java. IF-MAP is an XML based protocol for sharing data across
 * arbitrary components, specified by the Trusted Computing Group. IfmapJ is
 * maintained by the Trust@HsH group at the Hochschule Hannover. IfmapJ
 * was developed within the ESUKOM research project.
 * %%
 * Copyright (C) 2010 - 2013 Trust@HsH
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.hshannover.f4.trust.ifmapj.identifier.Device;
import de.hshannover.f4.trust.ifmapj.identifier.Identifiers;

/**
 * Trivial tests for {@link Device} class.
 * 
 * @author ibente
 *
 */
public class DeviceTest {
	public String name = "publisherid:number";

	@Test
	public void testDevice() {
		Device dev = Identifiers.createDev(null);
		assertNotNull(dev);
		assertNull(dev.getName());
		dev = Identifiers.createDev(name);
		assertEquals(name, dev.getName());
	}

	@Test
	public void testDeviceToString() {
		Device dev = Identifiers.createDevRandom();
		assertTrue(dev.getName().matches("[a-f0-9]{32}") ||
				dev.getName().matches("[A-Za-z0-9+/=]{24}"));
		dev = Identifiers.createDev("abc");
		assertEquals("dev{abc}", dev.toString());
	}
	
	@Test
	public void testDeviceWithRandomUUID() {
		Device dev = Identifiers.createDevRandomUUID();
		assertNotNull(dev);
		String regex_uuid = ("[a-f0-9]{8}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{4}-[a-f0-9]{12}");
		assertTrue(dev.getName().matches(regex_uuid));
	}
	
	@Test
	public void testDeviceWithPublisherId() {
		Device dev = Identifiers.createDevPubPrefixed("uid", "publisherid");
		assertNotNull(dev);
		assertEquals("publisherid:uid", dev.getName());
	}
}
