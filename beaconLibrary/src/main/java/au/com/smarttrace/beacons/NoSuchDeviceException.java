/*******************************************************************************
 * 
 *      This file is part of Beacon Transponder.
 *  
 *      Beacon Transponder is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *  
 *      Beacon Transponder is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *  
 *      You should have received a copy of the GNU General Public License
 *      along with Beacon Transponder.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *      Francesco Gabbrielli 2017
 *      
 ******************************************************************************/
package au.com.smarttrace.beacons;

public class NoSuchDeviceException extends Exception {

	private static final long serialVersionUID = -2625762713910825727L;

	public NoSuchDeviceException(String msg) {
		super(msg);
	}
	
	public NoSuchDeviceException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
