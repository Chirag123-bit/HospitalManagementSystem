package frontend;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.icepdf.ri.util.PropertiesManager;

import javax.swing.JScrollPane;

public class ReportViewingForm extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private SwingController control;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportViewingForm frame = new ReportViewingForm();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReportViewingForm() {
		setTitle("Report Viewing Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 954, 767);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 49, 873, 671);
		contentPane.add(scrollPane);
		String currentWorkingDir = System.getProperty("user.dir");
		pdfViewerICE(currentWorkingDir+"\\out.pdf");
	}
//		  void openpdf(String file){
//		  
//		    try {
//		           SwingController control=new SwingController();
//		            SwingViewBuilder factry=new SwingViewBuilder(control);
//		            JPanel veiwerCompntpnl=factry.buildViewerPanel();
//		            ComponentKeyBinding.install(control, veiwerCompntpnl);
//		            control.getDocumentViewController().setAnnotationCallback(
//		                    new org.icepdf.ri.common.MyAnnotationCallback(
//		                    control.getDocumentViewController()));
//		                   control.openDocument(file);
//		                   scrollPane.setViewportView(veiwerCompntpnl); 
//		        } catch (Exception ex) {
//		            JOptionPane.showMessageDialog(this,"Cannot Load Pdf");
//		        }
//		}
		  public static void pdfViewerICE(String file) {
		        String filePath = file;
		        // build a controller
		        SwingController controller = new SwingController();

		        // Build a SwingViewFactory configured with the controller
		        SwingViewBuilder factory = new SwingViewBuilder(controller);
		           PropertiesManager properties = new PropertiesManager(
		                System.getProperties(),
		                 ResourceBundle.getBundle(PropertiesManager.DEFAULT_MESSAGE_BUNDLE));

		   properties.set(PropertiesManager.PROPERTY_DEFAULT_ZOOM_LEVEL, "1.75");

		        // Use the factory to build a JPanel that is pre-configured
		        //with a complete, active Viewer UI.
		       JPanel viewerComponentPanel = factory.buildViewerPanel();

		        controller.openDocument(filePath);
		      }
		  
		  
}
