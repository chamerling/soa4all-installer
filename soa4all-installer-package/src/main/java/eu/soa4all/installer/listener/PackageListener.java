/**
 * PETALS: PETALS Services Platform Copyright (C) 2009 EBM WebSourcing
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 * 
 * Initial developer(s): EBM WebSourcing
 */
package eu.soa4all.installer.listener;

import java.io.File;

import com.izforge.izpack.Pack;
import com.izforge.izpack.PackFile;
import com.izforge.izpack.event.InstallerListener;
import com.izforge.izpack.installer.AutomatedInstallData;
import com.izforge.izpack.util.AbstractUIProgressHandler;

/**
 * @author chamerling - eBM WebSourcing
 *
 */
public class PackageListener implements InstallerListener {

	/**
	 * {@inheritDoc}
	 */
	public void afterDir(File arg0, PackFile arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	public void afterFile(File arg0, PackFile arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	public void afterInstallerInitialization(AutomatedInstallData arg0)
			throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	public void afterPack(Pack arg0, Integer arg1,
			AbstractUIProgressHandler arg2) throws Exception {
	}

	/**
	 * {@inheritDoc}
	 */
	public void afterPacks(AutomatedInstallData arg0,
			AbstractUIProgressHandler arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	public void beforeDir(File arg0, PackFile arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	public void beforeFile(File arg0, PackFile arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	public void beforePack(Pack arg0, Integer arg1,
			AbstractUIProgressHandler arg2) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	public void beforePacks(AutomatedInstallData arg0, Integer arg1,
			AbstractUIProgressHandler arg2) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isFileListener() {
		// TODO Auto-generated method stub
		return false;
	}

}
