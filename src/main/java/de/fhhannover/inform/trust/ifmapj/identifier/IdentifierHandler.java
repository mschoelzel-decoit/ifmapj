package de.fhhannover.inform.trust.ifmapj.identifier;

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

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.fhhannover.inform.trust.ifmapj.exception.MarshalException;
import de.fhhannover.inform.trust.ifmapj.exception.UnmarshalException;

/**
 * Provides generic access to marshal and unmarshal a {@link Identifier}
 * implementation from or to {@link Element} representation.
 * 
 * @author aw
 *
 */
public interface IdentifierHandler<T extends Identifier> {
	
	/**
	 * Create a {@link Element} instance representing the given {@link Identifier}.
	 * 
	 * @param i the {@link Identifier}
	 * @param doc the root {@link Document} where the element is to be added.
	 *		An implementation <b>must not</b> add the element to the given {@link Document}.
	 * @return the {@link Element} representation of the given {@link Identifier}.
	 * @throws MarshalException If some constraints for the {@link Identifier} are not fullfilled.
	 */
	public Element toElement(Identifier i, Document doc) throws MarshalException;
	
	/**
	 * Create a {@link Identifier} instance from the given {@link Element}.
	 * 
	 * @param el the {@link Element} instance
	 * @return an {@link Identifier} instance representation of the given {@link Element},
	 *	 or null if this {@link IdentifierHandler} was not able to parse
	 *	 the given {@link Element}.
	 * @throws UnmarshalException If some constraint of the {@link Identifier} was
	 *	 not fulfilled. <b>Note, if this handler is not responsible for the
	 *	 given XML, null has to be returned.</b>
	 */
	public T fromElement(Element el) throws UnmarshalException;
	
	/**
	 * @return the {@link Class} object for the {@link Identifier} implementation
	 *	 this implementation is able to handle.
	 */
	public Class<T> handles();
}
