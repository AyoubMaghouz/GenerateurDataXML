package Main;
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

		Float[][] T = {
				{(float) 9.6 , (float) 10.9},
				{(float) 8.7 , (float) 10.6},
				{(float) 9.9 , (float) 12.0},
				{(float) 12.4, (float) 16.4},
				{(float) 14.5, (float) 16.7},
				{(float) 17.2, (float) 20.6},
				{(float) 20.7, (float) 22.6},
				{(float) 19.8, (float) 21.7},
				{(float) 18.4, (float) 22.8},
				{(float) 16.2, (float) 18.1},
				{(float) 11.7, (float) 14.6},
				{(float) 10.7, (float) 14.1}
				};
				
//***************************************************Generateur *************************************************//

		Measures mesures = factory.createMeasures();
		mesures.setName("Température à Brest");
		
		Map theMap = factory.createMap();
		mesures.setTheMap(theMap);

		PointOnEarth mairie_de_Brest = factory.createPointOnEarth();
		mairie_de_Brest.setLatitude(48.39119189771081);
		mairie_de_Brest.setLongitude(-4.485018253326416);
		
		Location altitude_mairie_de_brest = factory.createLocation();
		altitude_mairie_de_brest.setAltitude(61.0);
		
		mairie_de_Brest.getTheDepths().add(altitude_mairie_de_brest);
		theMap.getThePoints().add(mairie_de_Brest);
		
		Data data = factory.createData();
		data.setName("Températures maximales moyennes");
		data.setDataType("Numerique");
		
		MetaData temperature = factory.createMetaData();
		temperature.setStandardName("Température");
		temperature.setUnit("°C");
		
		data.setTheMetadata(temperature);

		mesures.getTheData().add(data);
				
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
//		mairie_de_Brest.getTheDepths().add(altitude_mairie_de_brest);
//		theMap.getThePoints().add(mairie_de_Brest);
//		
//		Time t1 = factory.createTime();
//		Date d = new Date(2016, 9, 26, 15, 00);
//		t1.setLaDate(d);
//		
//		System.out.println("d1 ---> "+d);		
//		
//		Time t2 = factory.createTime();
//		Date d2 = new Date(2016, 9, 26, 22, 00);		
//		t2.setLaDate(d2);
//		
//		System.out.println("d2 ---> "+d2);		
//
//		System.out.println("t1 ---> "+t1);
//		System.out.println("t2 ---> "+t2);
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

//		Resource res = el.chargerModele("uri.mesures");
//		
//		Measures mesures2 = (Measures) res.getContents().get(0);
//		System.out.println(mesures2.getTheTimes().get(0));
//		System.out.println(mesures2.getTheTimes().get(1));

	}
	
	public static Date datePlus(Date d, Long t) {
		d.setTime(d.getTime()+t);
		return d;
	}
}
