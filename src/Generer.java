import java.util.Date;

import mesures.Data;
import mesures.Location;
import mesures.Map;
import mesures.Measures;
import mesures.MesuresFactory;
import mesures.MesuresPackage;
import mesures.MetaData;
import mesures.PointOnEarth;
import mesures.Time;
import mesures.Value;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.ecore.resource.Resource;

public class Generer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//***************************************************Declarations *************************************************//		
		
		MesuresPackage mp = MesuresPackage.eINSTANCE;
		MesuresFactory factory = MesuresFactory.eINSTANCE;
		ExtractLoad el = new ExtractLoad();

				
//***************************************************Test du sauvegarde *************************************************//		
		
//		Measures mesures = factory.createMeasures();
//		mesures.setName("Température à Brest");
//		
//		Map theMap = factory.createMap();
//		
//		PointOnEarth mairie_de_Brest = factory.createPointOnEarth();
//		mairie_de_Brest.setLatitude(48.39119189771081);
//		mairie_de_Brest.setLongitude(-4.485018253326416);
//		
//		Location altitude_mairie_de_brest = factory.createLocation();
//		altitude_mairie_de_brest.setAltitude(61.0);
//		
//		Time t1 = factory.createTime();
//		Date d = new Date(2016, 10, 26, 15, 00);
//		t1.setLaDate(d);
//		
//		System.out.println(d);
//		d.setTime(d.getTime()+3600000);
//		System.out.println(d);
//		
//		
//		Time t2 = factory.createTime();
//		d.setHours(22);
//		t2.setLaDate(d);
//		
//		Data data = factory.createData();
//		data.setName("Degré de température");
//		data.setDataType("Numerique");
//		
//		MetaData temperature = factory.createMetaData();
//		temperature.setStandardName("Température");
//		temperature.setUnit("°C");
//		
//		Value v1 = factory.createValue();
//		v1.setValue(18.0);
//		
//		Value v2 = factory.createValue();
//		v2.setValue(8.0);
//		
//		mesures.setTheMap(theMap);
//		mesures.getTheData().add(data);
//		mesures.getTheTimes().add(t1);
//		mesures.getTheTimes().add(t2);
//				
//		mairie_de_Brest.getTheDepths().add(altitude_mairie_de_brest);
//		theMap.getThePoints().add(mairie_de_Brest);
//		
//		data.setTheMetadata(temperature);
//		data.getTheValues().add(v1);
//		data.getTheValues().add(v2);
//		
//		t1.getTheValues().add(v1);
//		t2.getTheValues().add(v2);
//		
//		altitude_mairie_de_brest.getTheValues().add(v1);
//		altitude_mairie_de_brest.getTheValues().add(v2);
//		
//		el.sauverModele("uri.mesures", (EObject)mesures);
		
//***************************************************Test du chargement *************************************************//		

		Resource res = el.chargerModele("uri.mesures");
		
		Measures mesures2 = (Measures) res.getContents().get(0);
		System.out.println(mesures2);
//		
//		Map theMap = factory.createMap();
//		
//		PointOnEarth mairie_de_Brest = factory.createPointOnEarth();
//		mairie_de_Brest.setLatitude(48.39119189771081);
//		mairie_de_Brest.se(Measures) res.getContents().get(0)tLongitude(-4.485018253326416);
//		
//		Location altitude_mairie_de_brest = factory.createLocation();
//		altitude_mairie_de_brest.setAltitude(61.0);
//		
//		Time t1 = factory.createTime();
//		Date d = new Date(2016, 10, 26, 15, 00);
//		t1.setLaDate(d);
//		
//		System.out.println(d);
//		d.setTime(d.getTime()+3600000);
//		System.out.println(d);
//		
//		
//		Time t2 = factory.createTime();
//		d.setHours(22);
//		t2.setLaDate(d);
//		
//		Data data = factory.createData();
//		data.setName("Degré de température");
//		data.setDataType("Numerique");
//		
//		MetaData temperature = factory.createMetaData();
//		temperature.setStandardName("Température");
//		temperature.setUnit("°C");
//		
//		Value v1 = factory.createValue();
//		v1.setValue(18.0);
//		
//		Value v2 = factory.createValue();
//		v2.setValue(8.0);
//		
//		mesures.setTheMap(theMap);
//		mesures.getTheData().add(data);
//		mesures.getTheTimes().add(t1);
//		mesures.getTheTimes().add(t2);
//				
//		mairie_de_Brest.getTheDepths().add(altitude_mairie_de_brest);
//		theMap.getThePoints().add(mairie_de_Brest);
//		
//		data.setTheMetadata(temperature);
//		data.getTheValues().add(v1);
//		data.getTheValues().add(v2);
//		
//		t1.getTheValues().add(v1);
//		t2.getTheValues().add(v2);
//		
//		altitude_mairie_de_brest.getTheValues().add(v1);
//		altitude_mairie_de_brest.getTheValues().add(v2);
	}
	
	public static Date datePlus(Date d, Long t) {
		d.setTime(d.getTime()+t);
		return d;
	}
}
