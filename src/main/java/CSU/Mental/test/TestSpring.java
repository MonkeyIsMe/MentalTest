package CSU.Mental.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;



import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import CSU.Mental.Dao.FkindDao;
import CSU.Mental.Model.Choice;
import CSU.Mental.Model.Factor;
import CSU.Mental.Model.Problem;
import CSU.Mental.Model.Scale;
import CSU.Mental.Service.ChoiceService;
import CSU.Mental.Service.FkindService;
import CSU.Mental.Service.ProblemService;
import CSU.Mental.Service.ScaleService;
import CSU.Mental.Service.PatientService;
import CSU.Mental.Service.FactorService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestSpring {

	@Resource(name="FkindService")
	private FkindService FkindService;
	
	@Resource(name="ScaleService")
	private ScaleService ScaleService;
	
	@Resource(name="ChoiceService")
	private ChoiceService ChoiceService;
	
	@Resource(name="ProblemService")
	private ProblemService ProblemService;
	
	@Resource(name="PatientService")
	private PatientService PatientService;
	
	@Resource(name="FactorService")
	private FactorService FactorService;
	
	@Test
	public void QueryAllFkind() {
		System.out.println(FkindService.QueryAllFkind().size());
	}
	
	@Test
	public void AddFactor() {
		Factor fac = new Factor();
		fac.setFactorName("test_add");
		FactorService.AddFactor(fac);
	}
	
	@Test
	public void QueryChoiceByProblem() {
		System.out.println(ChoiceService.QueryChoiceByProblem(10));
	}
	
	@Test
	public void CountVaguePatient() {
		System.out.println(PatientService.CountVaguePatient("1"));
	}
	
	@Test
	public void QueryProblemByScale() {
		List<Problem> plist = ProblemService.QueryProblemByScale(1);
		for(Problem p : plist) {
			System.out.println(p.toString());
		}
	}

	
	@Test
	public void QueryProblem() {
		System.out.println(ProblemService.QueryProblem(0));
	}
	
	@Test
	public void QueryAddMutiplyProblem() {
		List<Problem> list = new ArrayList<Problem>();
		for(int i = 1; i <= 5; i ++) {
			Problem  p = new Problem();
			p.setProblemName(i + "_name");
			p.setProblemType(i + "_type");
			list.add(p);
		}
		List<Integer> inlist = ProblemService.AddMutiplyProblem(list);
		Iterator<Integer> itr = inlist.iterator();// Iterator声明对象，list.iterator返回Iterator对象
		while (itr.hasNext()) {// 判断是否有下一个数据
			System.out.println(itr.next());// 遍历输出
		}

	}
	
	@Test
	public void MakeScaleFactor() {
		JSONArray ja = new JSONArray();
		for(int i = 1;i <= 200; i ++) {
			JSONObject jo = new JSONObject();
			jo.put("factor_name", i);
			jo.put("factor_info", i);
			jo.put("factor_balance", i);
			jo.put("factor_den", i);
			jo.put("factor_formula", i);
			jo.put("factor_order", i);
			JSONArray rja = new JSONArray();
			for(int j = 1;j <= 3; j++) {
				JSONObject rjo = new JSONObject();
				rjo.put("refer_bscore", j * 100 + i);
				rjo.put("refer_escore", j * 100 + i);
				rjo.put("refer_sex", j * 100 + i);
				rjo.put("refer_bage", j * 100 + i);
				rjo.put("refer_eage", j * 100 + i);
				rjo.put("refer_suggestion", j * 100 + i);
				rja.add(rjo);
			}
			jo.put("refer_info", rja.toString());
			ja.add(jo);
		}
		System.out.println(ja.toString());
	}
	
	@Test
	public void AddScale() {
		
		Scale scale = new Scale();
		
		JSONObject ScaleInfo = new JSONObject();
		ScaleInfo.put("scale_name", "scale_name");
		ScaleInfo.put("scale_info", "scale_info");
		ScaleInfo.put("scale_guide", "scale_guide");
		ScaleInfo.put("sk_id", "1");
		
		JSONObject jo = JSONObject.fromObject(ScaleInfo);
		
		String scale_name = jo.getString("scale_name");
		String scale_info = jo.getString("scale_info");
		String scale_guide = jo.getString("scale_guide");
		String sk_id = jo.getString("sk_id");
		
		scale.setScaleGuide(scale_guide);
		scale.setScaleInfo(scale_info);
		scale.setScaleName(scale_name);
		scale.setSkinId(Integer.valueOf(sk_id));
		
		int sid = ScaleService.AddScale(scale);
	}
	
	@Test
	public void TestMap() {
		Map<Integer,Integer> mp = new HashMap<Integer, Integer>();
		for(int i = 1; i <= 5; i ++) {
			mp.put(i, i);
		}
		for(int i = 1; i <= 5; i += 2) {
			if(mp.get(i) == null) mp.put(i, i);
			else {
				System.out.println("进来了");
				int t = mp.get(i);
				t += i;
				mp.put(i, t);
			}
		}
		System.out.println(mp);
		Set<Integer> keySet = mp.keySet();
		Iterator<Integer> it = keySet.iterator();    
        while(it.hasNext()){                         
            //得到每一个key
            Integer key = it.next();
            //通过key获取对应的value
            Integer value = mp.get(key);
            System.out.println(key + "=" + value);
        }

	}
	
	
}
