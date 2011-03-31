/**
 * 
 */
package eu.soa4all.installer.util.panels;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;

import com.izforge.izpack.gui.IzPanelLayout;
import com.izforge.izpack.installer.InstallData;
import com.izforge.izpack.installer.InstallerFrame;
import com.izforge.izpack.installer.IzPanel;
import com.izforge.izpack.installer.ResourceManager;
import com.izforge.izpack.util.HyperlinkHandler;
import com.izforge.izpack.util.VariableSubstitutor;

/**
 * A HTML panel extension to display substituted variables in HTML resources.
 * Taken from snofyre project (http://code.google.com/p/snofyre/).
 * 
 * @author chamerling - PetalsLink
 * 
 */
public class XHtmlInfoPanel extends IzPanel {

	/** Resource prefix for panel. */
	protected String panelResourcePrefixStr;

	/** Resource name for panel content. */
	protected String panelResourceNameStr;

	/**
	 * The text area.
	 */
	protected JEditorPane textArea;
	private String info;

	/**
	 * The constructor.
	 * 
	 * @param parent
	 *            The parent.
	 * @param idata
	 *            The installation data.
	 */
	public XHtmlInfoPanel(InstallerFrame parent, InstallData idata) {
		this(parent, idata, "XHtmlInfoPanel");
	}

	/**
	 * Alternate constructor with additional parameters. For use with
	 * subclasses.
	 * 
	 * @param parent
	 *            The parent.
	 * @param idata
	 *            The installation data.
	 * @param resPrefixStr
	 *            prefix string for content resource name. above content.
	 */
	public XHtmlInfoPanel(InstallerFrame parent, InstallData idata,
			String resPrefixStr) {
		super(parent, idata, new IzPanelLayout());
		// setup given resource prefix and name:
		panelResourcePrefixStr = resPrefixStr;
		panelResourceNameStr = resPrefixStr + ".info";

		// We add the components
		try {
			textArea = new JEditorPane();
			textArea.setContentType("text/html; charset=utf-8");
			textArea.setEditable(false);
			textArea.addHyperlinkListener(new HyperlinkHandler());
			JScrollPane scroller = new JScrollPane(textArea);
			textArea.setPage(loadHTMLInfoContent());
			// set caret so beginning of file is displayed:
			textArea.setCaretPosition(0);
			add(scroller, NEXT_LINE);
		} catch (Exception err) {
			err.printStackTrace();
		}
		getLayoutHelper().completeLayout();
	}

	/*
	 * loads the content of the info resource as text so that it can be parsed
	 * afterwards
	 */
	private URL loadHTMLInfoContent() {
		if (getMetadata() != null && getMetadata().getPanelid() != null) {
			try {
				String panelSpecificResName = panelResourcePrefixStr + '.'
						+ this.getMetadata().getPanelid();
				String panelspecificResContent = ResourceManager.getInstance()
						.getTextResource(panelSpecificResName);
				if (panelspecificResContent != null) {
					panelResourceNameStr = panelSpecificResName;
				}
			} catch (Exception e) {
				// Those ones can be skipped
			}
		}

		try {
			return ResourceManager.getInstance().getURL(panelResourceNameStr);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	/**
	 * Indicates wether the panel has been validated or not.
	 * 
	 * @return Always true.
	 */
	public boolean isValidated() {
		return true;
	}

	/**
	 * Loads the info text.
	 */
	private void loadInfo() {
		try {
			// We read it
			info = ResourceManager.getInstance().getTextResource(
					"XHtmlInfoPanel.info");
		} catch (Exception err) {
			info = "Error : could not load the info text !";
		}
	}

	/**
	 * Parses the text for special variables.
	 */
	private void parseText() {
		try {
			// Initialize the variable substitutor
			VariableSubstitutor vs = new VariableSubstitutor(
					idata.getVariables());

			// Parses the info text
			info = vs.substitute(info, null);
			
			// Specific : substitute lists
			// get the lists defined in the resource
			List<String> lists = getLists(info);
			for(String string : lists) {
				StringBuffer sb = new StringBuffer("<ul>");
				Properties props = idata.getVariables();
				
				Set<Object> keys = props.keySet();
				List<String> values = new ArrayList<String>();
				for(Object key : keys) {
					String k = (String)key;
					if(k.startsWith(string)) {
						values.add(props.getProperty(k));
					}
				}
				
				for(String value : values) {
					sb.append("<li>");
					sb.append(value);
					sb.append("</li>");
				}
				
				sb.append("</ul>");
				info = info.replaceAll(toList(string), sb.toString());
			}
			
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
	
	String toList(String name) {
		return "LIST\\(" + name + "\\)";
	}
	
	List<String> getLists(String input) {
		List<String> result = new ArrayList<String>(0);
		int from = 0;
		if (input != null && input.length() > 0) {
			while (from < input.length() - 1) {
				int start = input.indexOf("LIST(", from);
				if (start < 0) {
					// stop
					from = input.length();
				} else {
					int stop = input.indexOf(")", start + "LIST(".length());
					String listName = input.substring(start + "LIST(".length(),
							stop);
					if (listName != null && listName.length() > 0) {
						result.add(listName);
					}
					from = stop;
				}
			}
		}
		return result;
	}

	public void panelActivate() {
		// load and parse text
		loadInfo();
		parseText();
		textArea.setText(info);
		// set caret so beginning of file is displayed:
		textArea.setCaretPosition(0);
	}
}
