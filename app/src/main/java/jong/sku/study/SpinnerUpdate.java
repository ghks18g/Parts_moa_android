package jong.sku.study;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SpinnerUpdate {
    private Spinner makeridSpinner;
    private String makerid = "";
    private String makerName="";
    private Spinner lineUpIdSpinner;
    private String lineUpid = "";
    private String lineUpName = "";
    private Spinner yearIdSpinner;
    private String yearId = "";
    private String year = "";
    private String TAG = "SpinnerUpdate";
    List<CommonSpinner> makerSpinnerItems, lineUpSpinnerItems, yearSpinnerItems;

    public SpinnerUpdate(Spinner makeridSpinner,
                         Spinner lineUpIdSpinner,
                         Spinner yearIdSpinner){
        this.makeridSpinner = makeridSpinner;
        this.lineUpIdSpinner = lineUpIdSpinner;
        this.yearIdSpinner = yearIdSpinner;
    }

    public String getCarInfoId(){
        Log.d(TAG,makerid+lineUpid+yearId);
        return makerid+lineUpid+yearId;
    }

    public String getCarInfo(){
        Log.d(TAG, makerName+"/"+lineUpName+"/"+year);
        return makerName+"_"+lineUpName+"_"+year;
    }

    public void Spinner_maker_lineup_year_Update(Context context){

        // ------제조사 id --------
        makerSpinnerItems = new ArrayList<CommonSpinner>();
        makerSpinnerItems.add(new CommonSpinner("3011","현대"));
        makerSpinnerItems.add(new CommonSpinner("3012","제네시스"));
        makerSpinnerItems.add(new CommonSpinner("3013","기아"));
        makerSpinnerItems.add(new CommonSpinner("3014","쉐보레"));
        makerSpinnerItems.add(new CommonSpinner("3015","르노삼성"));
        makerSpinnerItems.add(new CommonSpinner("3016","쌍용"));
        makerSpinnerItems.add(new CommonSpinner("3021","bmw"));
        makerSpinnerItems.add(new CommonSpinner("3028","닛산"));
        makerSpinnerItems.add(new CommonSpinner("302a","랜드로버"));
        makerSpinnerItems.add(new CommonSpinner("3025","렉서스"));
        makerSpinnerItems.add(new CommonSpinner("302c","링컨"));
        makerSpinnerItems.add(new CommonSpinner("302d","마세라티"));
        makerSpinnerItems.add(new CommonSpinner("3026","미니"));
        makerSpinnerItems.add(new CommonSpinner("3022","벤츠"));
        makerSpinnerItems.add(new CommonSpinner("302f","벤틀리"));
        makerSpinnerItems.add(new CommonSpinner("302g","볼보"));
        makerSpinnerItems.add(new CommonSpinner("302j","시트로엥"));
        makerSpinnerItems.add(new CommonSpinner("3023","아우디"));
        makerSpinnerItems.add(new CommonSpinner("302k","인피니티"));
        makerSpinnerItems.add(new CommonSpinner("302l","재규어"));
        makerSpinnerItems.add(new CommonSpinner("302m","Jeep"));
        makerSpinnerItems.add(new CommonSpinner("302n","캐딜락"));
        makerSpinnerItems.add(new CommonSpinner("302p","토요타"));
        makerSpinnerItems.add(new CommonSpinner("3027","포드"));
        makerSpinnerItems.add(new CommonSpinner("302q","포르쉐"));
        makerSpinnerItems.add(new CommonSpinner("3024","폭스바겐"));
        makerSpinnerItems.add(new CommonSpinner("302r","푸조"));
        makerSpinnerItems.add(new CommonSpinner("302u","혼다"));

        ArrayAdapter<CommonSpinner> makeridAdapter = new ArrayAdapter<CommonSpinner>(context,android.R.layout.simple_spinner_item, makerSpinnerItems);
        makeridAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        makeridSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CommonSpinner makeridList = (CommonSpinner)parent.getItemAtPosition(position);
                makerid = makeridList.getKey();
                makerName = makeridList.getValue();

                Log.d(TAG,"key:"+makerid);
                //------ 차종 id -----------
                lineUpSpinnerItems = new ArrayList<CommonSpinner>();
                if (makerid.equals("3011")){    // hyundai
                    lineUpSpinnerItems.add(new CommonSpinner("10","i30"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","i40"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","그랜저"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","마이티"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","맥스크루즈"));
                    lineUpSpinnerItems.add(new CommonSpinner("60","베라크루즈"));
                    lineUpSpinnerItems.add(new CommonSpinner("65","베뉴"));
                    lineUpSpinnerItems.add(new CommonSpinner("70","베르나"));
                    lineUpSpinnerItems.add(new CommonSpinner("80","벨로스터"));
                    lineUpSpinnerItems.add(new CommonSpinner("90","스타렉스"));
                    lineUpSpinnerItems.add(new CommonSpinner("a0","산타페"));
                    lineUpSpinnerItems.add(new CommonSpinner("b0","소나타"));
                    lineUpSpinnerItems.add(new CommonSpinner("d0","아반떼"));
                    lineUpSpinnerItems.add(new CommonSpinner("f0","아이오닉"));
                    lineUpSpinnerItems.add(new CommonSpinner("g0","에쿠스"));
                    lineUpSpinnerItems.add(new CommonSpinner("j3","코나"));
                    lineUpSpinnerItems.add(new CommonSpinner("n0","투싼"));
                    lineUpSpinnerItems.add(new CommonSpinner("o5","펠리세이드"));
                    lineUpSpinnerItems.add(new CommonSpinner("p0","포터"));
                } else if(makerid.equals("3012")){  // genesis
                    lineUpSpinnerItems.add(new CommonSpinner("05","g70"));
                    lineUpSpinnerItems.add(new CommonSpinner("10","g80"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","g90"));
                } else if(makerid.equals("3013")){          // kia
                    lineUpSpinnerItems.add(new CommonSpinner("10","k3"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","k5"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","k7"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","k9"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","니로"));
                    lineUpSpinnerItems.add(new CommonSpinner("60","레이"));
                    lineUpSpinnerItems.add(new CommonSpinner("70","로체"));
                    lineUpSpinnerItems.add(new CommonSpinner("80","모닝"));
                    lineUpSpinnerItems.add(new CommonSpinner("90","모하비"));
                    lineUpSpinnerItems.add(new CommonSpinner("a0","봉고"));
                    lineUpSpinnerItems.add(new CommonSpinner("a2","셀토스"));
                    lineUpSpinnerItems.add(new CommonSpinner("a4","스토닉"));
                    lineUpSpinnerItems.add(new CommonSpinner("b0","스팅어"));
                    lineUpSpinnerItems.add(new CommonSpinner("c0","스포티지"));
                    lineUpSpinnerItems.add(new CommonSpinner("d0","쎄라토"));
                    lineUpSpinnerItems.add(new CommonSpinner("e0","쏘렌토"));
                    lineUpSpinnerItems.add(new CommonSpinner("f0","쏘울"));
                    lineUpSpinnerItems.add(new CommonSpinner("g0","오피러스"));
                    lineUpSpinnerItems.add(new CommonSpinner("h0","옵티마"));
                    lineUpSpinnerItems.add(new CommonSpinner("i0","카니발"));
                    lineUpSpinnerItems.add(new CommonSpinner("j0","카렌스"));
                    lineUpSpinnerItems.add(new CommonSpinner("k0","포르테"));
                    lineUpSpinnerItems.add(new CommonSpinner("l0","프라이드"));
                } else if(makerid.equals("3014")){          // chevorlet
                    lineUpSpinnerItems.add(new CommonSpinner("20","다마스"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","라보"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","라세티"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","마티즈"));
                    lineUpSpinnerItems.add(new CommonSpinner("60","말리부"));
                    lineUpSpinnerItems.add(new CommonSpinner("80","볼트"));
                    lineUpSpinnerItems.add(new CommonSpinner("a0","스파크"));
                    lineUpSpinnerItems.add(new CommonSpinner("b0","아베오"));
                    lineUpSpinnerItems.add(new CommonSpinner("c0","알페온"));
                    lineUpSpinnerItems.add(new CommonSpinner("d0","올란도"));
                    lineUpSpinnerItems.add(new CommonSpinner("e0","윈스톰"));
                    lineUpSpinnerItems.add(new CommonSpinner("e5","이쿼녹스"));
                    lineUpSpinnerItems.add(new CommonSpinner("f0","임팔라"));
                    lineUpSpinnerItems.add(new CommonSpinner("h0","카마로"));
                    lineUpSpinnerItems.add(new CommonSpinner("j0","캡티바"));
                    lineUpSpinnerItems.add(new CommonSpinner("j5","콜로라도"));
                    lineUpSpinnerItems.add(new CommonSpinner("k0","크루즈"));
                    lineUpSpinnerItems.add(new CommonSpinner("l0","토스카"));
                    lineUpSpinnerItems.add(new CommonSpinner("l5","트레버스"));
                    lineUpSpinnerItems.add(new CommonSpinner("m0","트렉스"));
                } else if(makerid.equals("3015")){      // renault_samsung
                    lineUpSpinnerItems.add(new CommonSpinner("10","sm3"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","sm5"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","sm6"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","sm7"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","qm3"));
                    lineUpSpinnerItems.add(new CommonSpinner("60","qm5"));
                    lineUpSpinnerItems.add(new CommonSpinner("70","qm6"));
                    lineUpSpinnerItems.add(new CommonSpinner("72","마스터"));
                    lineUpSpinnerItems.add(new CommonSpinner("75","클리오"));
                    lineUpSpinnerItems.add(new CommonSpinner("80","트위지"));
                } else if(makerid.equals("3016")){      // ssangyong
                    lineUpSpinnerItems.add(new CommonSpinner("10","렉스턴"));
                    lineUpSpinnerItems.add(new CommonSpinner("15","렉스턴스포츠"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","로디우스"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","무쏘"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","액티언"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","체어맨"));
                    lineUpSpinnerItems.add(new CommonSpinner("60","카이런"));
                    lineUpSpinnerItems.add(new CommonSpinner("70","코란도"));
                    lineUpSpinnerItems.add(new CommonSpinner("80","코란도스포츠"));
                    lineUpSpinnerItems.add(new CommonSpinner("90","코란도투리스모"));
                    lineUpSpinnerItems.add(new CommonSpinner("a0","티볼리"));
                    lineUpSpinnerItems.add(new CommonSpinner("b0","티볼리에어"));
                } else if(makerid.equals("3021")){      //bmw
                    lineUpSpinnerItems.add(new CommonSpinner("10","1시리즈"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","2시리즈"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","3시리즈"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","4시리즈"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","5시리즈"));
                    lineUpSpinnerItems.add(new CommonSpinner("60","6시리즈"));
                    lineUpSpinnerItems.add(new CommonSpinner("70","7시리즈"));
                    lineUpSpinnerItems.add(new CommonSpinner("80","8시리즈"));
                    lineUpSpinnerItems.add(new CommonSpinner("90","M1"));
                    lineUpSpinnerItems.add(new CommonSpinner("a0","M2"));
                    lineUpSpinnerItems.add(new CommonSpinner("b0","M3"));
                    lineUpSpinnerItems.add(new CommonSpinner("c0","M4"));
                    lineUpSpinnerItems.add(new CommonSpinner("d0","M5"));
                    lineUpSpinnerItems.add(new CommonSpinner("e0","M6"));
                    lineUpSpinnerItems.add(new CommonSpinner("e5","M8"));
                    lineUpSpinnerItems.add(new CommonSpinner("f0","X1"));
                    lineUpSpinnerItems.add(new CommonSpinner("f5","X2"));
                    lineUpSpinnerItems.add(new CommonSpinner("g0","X3"));
                    lineUpSpinnerItems.add(new CommonSpinner("h0","X4"));
                    lineUpSpinnerItems.add(new CommonSpinner("i0","X5"));
                    lineUpSpinnerItems.add(new CommonSpinner("j0","X6"));
                    lineUpSpinnerItems.add(new CommonSpinner("l0","X7"));
                    lineUpSpinnerItems.add(new CommonSpinner("m0","Z3"));
                    lineUpSpinnerItems.add(new CommonSpinner("n0","Z4"));
                    lineUpSpinnerItems.add(new CommonSpinner("o0","Z8"));
                    lineUpSpinnerItems.add(new CommonSpinner("r0","i3"));
                    lineUpSpinnerItems.add(new CommonSpinner("s0","i8"));
                }
                else if(makerid.equals("3028")){  // 닛산
                    lineUpSpinnerItems.add(new CommonSpinner("20","GT_R"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","로그"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","리프"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","맥시마"));
                    lineUpSpinnerItems.add(new CommonSpinner("60","무라노"));
                    lineUpSpinnerItems.add(new CommonSpinner("70","알티마"));
                    lineUpSpinnerItems.add(new CommonSpinner("80","쥬크"));
                    lineUpSpinnerItems.add(new CommonSpinner("90","캐시카이"));
                    lineUpSpinnerItems.add(new CommonSpinner("a0","큐브"));
                    lineUpSpinnerItems.add(new CommonSpinner("b0","패스파인더"));
                }
                else if(makerid.equals("302a")){  // 랜드로버
                    lineUpSpinnerItems.add(new CommonSpinner("10","디스커버리"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","디스커버리_스포츠"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","레인지로버"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","레인지로버_벨라"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","레인지로버_스포츠"));
                    lineUpSpinnerItems.add(new CommonSpinner("60","레인지로버_이보크"));
                    lineUpSpinnerItems.add(new CommonSpinner("70","프리랜더"));
                }
                else if(makerid.equals("3025")){  // lexus
                    lineUpSpinnerItems.add(new CommonSpinner("10","ct"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","is"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","es"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","gs"));
                    lineUpSpinnerItems.add(new CommonSpinner("70","ls"));
                }
                else if(makerid.equals("302c")){  // 링컨
                    lineUpSpinnerItems.add(new CommonSpinner("10","LS"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","MKS"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","MKX"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","MKZ"));
                    lineUpSpinnerItems.add(new CommonSpinner("55","노틸러스"));
                    lineUpSpinnerItems.add(new CommonSpinner("60","에비에이터"));
                    lineUpSpinnerItems.add(new CommonSpinner("70","컨티넨탈"));
                }
                else if(makerid.equals("302d")){  // 마세라티
                    lineUpSpinnerItems.add(new CommonSpinner("20","그란투리스모"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","기블리"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","르반떼"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","콰트로포르테"));
                }
                else if(makerid.equals("3026")){  // 미니
                    lineUpSpinnerItems.add(new CommonSpinner("20","컨버터블"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","컨트리맨"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","쿠퍼"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","클럽맨"));
                    lineUpSpinnerItems.add(new CommonSpinner("70","해치"));
                }
                else if(makerid.equals("3022")){  // 벤츠
                    lineUpSpinnerItems.add(new CommonSpinner("10","A클래스"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","B클래스"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","C클래스"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","CL클래스"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","CLA"));
                    lineUpSpinnerItems.add(new CommonSpinner("60","CLK"));
                    lineUpSpinnerItems.add(new CommonSpinner("70","CLS"));
                    lineUpSpinnerItems.add(new CommonSpinner("80","E클래스"));
                    lineUpSpinnerItems.add(new CommonSpinner("85","EQC"));
                    lineUpSpinnerItems.add(new CommonSpinner("90","G클래스"));
                    lineUpSpinnerItems.add(new CommonSpinner("b0","GLA"));
                    lineUpSpinnerItems.add(new CommonSpinner("c0","GLC"));
                    lineUpSpinnerItems.add(new CommonSpinner("d0","GLE"));
                    lineUpSpinnerItems.add(new CommonSpinner("e0","GLK"));
                    lineUpSpinnerItems.add(new CommonSpinner("f0","GLS"));
                    lineUpSpinnerItems.add(new CommonSpinner("g0","M클래스"));
                    lineUpSpinnerItems.add(new CommonSpinner("i0","S클래스"));
                    lineUpSpinnerItems.add(new CommonSpinner("j0","SL"));
                    lineUpSpinnerItems.add(new CommonSpinner("k0","SLC"));
                    lineUpSpinnerItems.add(new CommonSpinner("l0","SLK"));
                    lineUpSpinnerItems.add(new CommonSpinner("o0","AMG_GT"));
                }
                else if(makerid.equals("302f")){  // 벤틀리
                    lineUpSpinnerItems.add(new CommonSpinner("10","뮬산"));
                    lineUpSpinnerItems.add(new CommonSpinner("15","벤테이가"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","컨티넨탈"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","플라잉스퍼"));
                }
                else if(makerid.equals("302g")){  // 볼보
                    lineUpSpinnerItems.add(new CommonSpinner("10","c30"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","c70"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","s40"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","s60"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","s70"));
                    lineUpSpinnerItems.add(new CommonSpinner("60","s80"));
                    lineUpSpinnerItems.add(new CommonSpinner("70","s90"));
                    lineUpSpinnerItems.add(new CommonSpinner("80","v40"));
                    lineUpSpinnerItems.add(new CommonSpinner("90","v50"));
                    lineUpSpinnerItems.add(new CommonSpinner("a0","v60"));
                    lineUpSpinnerItems.add(new CommonSpinner("b0","v70"));
                    lineUpSpinnerItems.add(new CommonSpinner("d0","xc60"));
                    lineUpSpinnerItems.add(new CommonSpinner("e0","xc70"));
                    lineUpSpinnerItems.add(new CommonSpinner("f0","xc90"));
                }
                else if(makerid.equals("302j")){  // 시트로엥
                    lineUpSpinnerItems.add(new CommonSpinner("03","c3_에어크로스"));
                    lineUpSpinnerItems.add(new CommonSpinner("07","c4_스페이스투어러"));
                    lineUpSpinnerItems.add(new CommonSpinner("10","c4_칵투스"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","c4_피카소"));
                    lineUpSpinnerItems.add(new CommonSpinner("25","c4_에어크로스"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","ds3"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","ds4"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","ds5"));
                    lineUpSpinnerItems.add(new CommonSpinner("60","ds7"));
                }
                else if(makerid.equals("3023")){  // 아우
                    lineUpSpinnerItems.add(new CommonSpinner("10","A1"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","A3"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","A4"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","A5"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","A6"));
                    lineUpSpinnerItems.add(new CommonSpinner("60","A7"));
                    lineUpSpinnerItems.add(new CommonSpinner("70","A8"));
                    lineUpSpinnerItems.add(new CommonSpinner("80","S3"));
                    lineUpSpinnerItems.add(new CommonSpinner("90","S4"));
                    lineUpSpinnerItems.add(new CommonSpinner("a0","S5"));
                    lineUpSpinnerItems.add(new CommonSpinner("b0","S6"));
                    lineUpSpinnerItems.add(new CommonSpinner("c0","S7"));
                    lineUpSpinnerItems.add(new CommonSpinner("d0","S8"));
                    lineUpSpinnerItems.add(new CommonSpinner("e0","TT"));
                    lineUpSpinnerItems.add(new CommonSpinner("f0","TTS"));
                    lineUpSpinnerItems.add(new CommonSpinner("g0","TT_RS"));
                    lineUpSpinnerItems.add(new CommonSpinner("h0","RS3"));
                    lineUpSpinnerItems.add(new CommonSpinner("i0","RS4"));
                    lineUpSpinnerItems.add(new CommonSpinner("j0","RS5"));
                    lineUpSpinnerItems.add(new CommonSpinner("k0","RS6"));
                    lineUpSpinnerItems.add(new CommonSpinner("l0","RS7"));
                    lineUpSpinnerItems.add(new CommonSpinner("m0","Q3"));
                    lineUpSpinnerItems.add(new CommonSpinner("n0","Q5"));
                    lineUpSpinnerItems.add(new CommonSpinner("o0","Q7"));
                    lineUpSpinnerItems.add(new CommonSpinner("p0","SQ5"));
                    lineUpSpinnerItems.add(new CommonSpinner("q0","SQ7"));
                    lineUpSpinnerItems.add(new CommonSpinner("r0","RS_Q3"));
                    lineUpSpinnerItems.add(new CommonSpinner("s0","R8"));
                }
                else if(makerid.equals("302k")){  // 인피니티
                    lineUpSpinnerItems.add(new CommonSpinner("10","EX"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","FX"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","G"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","JX35"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","M"));
                    lineUpSpinnerItems.add(new CommonSpinner("60","Q30"));
                    lineUpSpinnerItems.add(new CommonSpinner("70","Q50"));
                    lineUpSpinnerItems.add(new CommonSpinner("80","Q60"));
                    lineUpSpinnerItems.add(new CommonSpinner("90","Q70"));
                    lineUpSpinnerItems.add(new CommonSpinner("a0","QX30"));
                    lineUpSpinnerItems.add(new CommonSpinner("b0","QX50"));
                    lineUpSpinnerItems.add(new CommonSpinner("c0","QX60"));
                    lineUpSpinnerItems.add(new CommonSpinner("d0","QX70"));
                    lineUpSpinnerItems.add(new CommonSpinner("e0","QX80"));
                }
                else if(makerid.equals("302l")){  // 재규어
                    lineUpSpinnerItems.add(new CommonSpinner("05","E_FACE"));
                    lineUpSpinnerItems.add(new CommonSpinner("10","F_TYPE"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","F_FACE"));
                    lineUpSpinnerItems.add(new CommonSpinner("25","I_FACE"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","S_TYPE"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","X_TYPE"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","XE"));
                    lineUpSpinnerItems.add(new CommonSpinner("60","XF"));
                    lineUpSpinnerItems.add(new CommonSpinner("70","XJ"));
                    lineUpSpinnerItems.add(new CommonSpinner("80","XK"));
                }
                else if(makerid.equals("302m")){  // Jeep
                    lineUpSpinnerItems.add(new CommonSpinner("10","그랜드체로키"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","랭글러"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","레니게이드"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","체로키"));
                    lineUpSpinnerItems.add(new CommonSpinner("45","커맨더"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","컴패스"));
                }
                else if(makerid.equals("302n")){  // 캐딜락
                    lineUpSpinnerItems.add(new CommonSpinner("10","ATS"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","BLS"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","CT6"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","CTS"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","DTS"));
                    lineUpSpinnerItems.add(new CommonSpinner("60","SRX"));
                    lineUpSpinnerItems.add(new CommonSpinner("70","STS"));
                    lineUpSpinnerItems.add(new CommonSpinner("80","XT5"));
                    lineUpSpinnerItems.add(new CommonSpinner("90","드빌"));
                    lineUpSpinnerItems.add(new CommonSpinner("a0","스빌"));
                    lineUpSpinnerItems.add(new CommonSpinner("b0","에스컬레이드"));
                }
                else if(makerid.equals("302p")){  // 토요타
                    lineUpSpinnerItems.add(new CommonSpinner("10","86"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","FJ크루저"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","RAV4"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","벤자"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","시에나"));
                    lineUpSpinnerItems.add(new CommonSpinner("60","아발론"));
                    lineUpSpinnerItems.add(new CommonSpinner("70","캠리"));
                    lineUpSpinnerItems.add(new CommonSpinner("80","코롤라"));
                    lineUpSpinnerItems.add(new CommonSpinner("90","프리우스"));
                }
                else if(makerid.equals("3027")){  // 포드
                    lineUpSpinnerItems.add(new CommonSpinner("10","F시리즈"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","머스탱"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","몬데오"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","이스케이프"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","익스플로러"));
                    lineUpSpinnerItems.add(new CommonSpinner("60","쿠가"));
                    lineUpSpinnerItems.add(new CommonSpinner("70","토러스"));
                    lineUpSpinnerItems.add(new CommonSpinner("80","five_hundred"));
                    lineUpSpinnerItems.add(new CommonSpinner("90","포커스"));
                    lineUpSpinnerItems.add(new CommonSpinner("a0","퓨전"));
                }
                else if(makerid.equals("302q")){  // 포르쉐
                    lineUpSpinnerItems.add(new CommonSpinner("10","911"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","마칸"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","박스터"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","카이맨"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","카이엔"));
                    lineUpSpinnerItems.add(new CommonSpinner("60","파나메라"));
                }
                else if(makerid.equals("3024")){  // 폭스바겐
                    lineUpSpinnerItems.add(new CommonSpinner("10","CC"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","EOS"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","골프"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","비틀"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","시로코"));
                    lineUpSpinnerItems.add(new CommonSpinner("55","아테온"));
                    lineUpSpinnerItems.add(new CommonSpinner("60","제타"));
                    lineUpSpinnerItems.add(new CommonSpinner("70","파사트"));
                    lineUpSpinnerItems.add(new CommonSpinner("80","페이톤"));
                    lineUpSpinnerItems.add(new CommonSpinner("90","폴로"));
                    lineUpSpinnerItems.add(new CommonSpinner("a0","투아렉"));
                    lineUpSpinnerItems.add(new CommonSpinner("b0","티구안"));
                }
                else if(makerid.equals("302r")){  // 푸조
                    lineUpSpinnerItems.add(new CommonSpinner("10","206"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","207"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","208"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","307"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","308"));
                    lineUpSpinnerItems.add(new CommonSpinner("60","407"));
                    lineUpSpinnerItems.add(new CommonSpinner("70","508"));
                    lineUpSpinnerItems.add(new CommonSpinner("80","607"));
                    lineUpSpinnerItems.add(new CommonSpinner("90","2008"));
                    lineUpSpinnerItems.add(new CommonSpinner("a0","3008"));
                    lineUpSpinnerItems.add(new CommonSpinner("a5","5008"));
                    lineUpSpinnerItems.add(new CommonSpinner("b0","RCZ"));
                }
                else if(makerid.equals("302u")){  // 혼다
                    lineUpSpinnerItems.add(new CommonSpinner("10","CR_V"));
                    lineUpSpinnerItems.add(new CommonSpinner("20","CR_Z"));
                    lineUpSpinnerItems.add(new CommonSpinner("30","HR_V"));
                    lineUpSpinnerItems.add(new CommonSpinner("40","레전드"));
                    lineUpSpinnerItems.add(new CommonSpinner("50","시빅"));
                    lineUpSpinnerItems.add(new CommonSpinner("60","어코드"));
                    lineUpSpinnerItems.add(new CommonSpinner("70","오디세이"));
                    lineUpSpinnerItems.add(new CommonSpinner("80","인사이트"));
                    lineUpSpinnerItems.add(new CommonSpinner("90","크로스투어"));
                    lineUpSpinnerItems.add(new CommonSpinner("a0","파일럿"));
                }

                ArrayAdapter<CommonSpinner> lineUpIdAdapter = new ArrayAdapter<CommonSpinner>(context,android.R.layout.simple_spinner_item,lineUpSpinnerItems);
                lineUpIdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                lineUpIdSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        CommonSpinner lineUpIdList = (CommonSpinner) parent.getItemAtPosition(position);
                        lineUpid = lineUpIdList.getKey();
                        lineUpName = lineUpIdList.getValue();
                        Log.d(TAG,"key:"+lineUpid);

                        //---------- 년식 id -------------
                        yearSpinnerItems = new ArrayList<CommonSpinner>();
                        if(makerid.equals("3011")){     //hyundai
                            if(lineUpid.equals("10")){
                                yearSpinnerItems.add(new CommonSpinner("10","07~11"));
                                yearSpinnerItems.add(new CommonSpinner("20","11~16"));
                                yearSpinnerItems.add(new CommonSpinner("30","16~"));
                            }else if(lineUpid.equals("20")){
                                yearSpinnerItems.add(new CommonSpinner("10","11~"));
                            }else if(lineUpid.equals("30")){
                                yearSpinnerItems.add(new CommonSpinner("10","98~05"));
                                yearSpinnerItems.add(new CommonSpinner("20","05~11"));
                                yearSpinnerItems.add(new CommonSpinner("30","11~16"));
                                yearSpinnerItems.add(new CommonSpinner("40","16~"));
                            }else if(lineUpid.equals("40")){
                                yearSpinnerItems.add(new CommonSpinner("10","98~15"));
                                yearSpinnerItems.add(new CommonSpinner("20","15~"));
                            }else if(lineUpid.equals("50")){
                                yearSpinnerItems.add(new CommonSpinner("10","13~19"));
                            }else if(lineUpid.equals("60")){
                                yearSpinnerItems.add(new CommonSpinner("10","06~15"));
                            }else if(lineUpid.equals("65")){
                                yearSpinnerItems.add(new CommonSpinner("10","19~"));
                            }else if(lineUpid.equals("70")){
                                yearSpinnerItems.add(new CommonSpinner("10","99~05"));
                                yearSpinnerItems.add(new CommonSpinner("20","05~10"));
                            }else if(lineUpid.equals("80")){
                                yearSpinnerItems.add(new CommonSpinner("10","11~17"));
                                yearSpinnerItems.add(new CommonSpinner("20","18~"));
                            }else if(lineUpid.equals("90")){
                                yearSpinnerItems.add(new CommonSpinner("10","97~07"));
                                yearSpinnerItems.add(new CommonSpinner("20","07~"));
                            }else if(lineUpid.equals("a0")){
                                yearSpinnerItems.add(new CommonSpinner("10","05~12"));
                                yearSpinnerItems.add(new CommonSpinner("20","12~18"));
                                yearSpinnerItems.add(new CommonSpinner("30","18~"));
                            }else if(lineUpid.equals("b0")){
                                yearSpinnerItems.add(new CommonSpinner("10","98~04"));
                                yearSpinnerItems.add(new CommonSpinner("20","04~09"));
                                yearSpinnerItems.add(new CommonSpinner("30","09~14"));
                                yearSpinnerItems.add(new CommonSpinner("40","14~19"));
                                yearSpinnerItems.add(new CommonSpinner("50","19~"));
                            }else if(lineUpid.equals("d0")){
                                yearSpinnerItems.add(new CommonSpinner("10","00~06"));
                                yearSpinnerItems.add(new CommonSpinner("20","06~10"));
                                yearSpinnerItems.add(new CommonSpinner("30","10~15"));
                                yearSpinnerItems.add(new CommonSpinner("40","15~"));
                            }else if(lineUpid.equals("f0")){
                                yearSpinnerItems.add(new CommonSpinner("10","16~"));
                            }else if(lineUpid.equals("g0")){
                                yearSpinnerItems.add(new CommonSpinner("10","99~08"));
                                yearSpinnerItems.add(new CommonSpinner("20","09~15"));
                            }else if(lineUpid.equals("j3")){
                                yearSpinnerItems.add(new CommonSpinner("10","17~"));
                            }else if(lineUpid.equals("n0")){
                                yearSpinnerItems.add(new CommonSpinner("10","01~09"));
                                yearSpinnerItems.add(new CommonSpinner("20","09~15"));
                                yearSpinnerItems.add(new CommonSpinner("30","15~"));
                            }else if(lineUpid.equals("o5")){
                                yearSpinnerItems.add(new CommonSpinner("10","18~"));
                            }else if(lineUpid.equals("p0")){
                                yearSpinnerItems.add(new CommonSpinner("10","04~"));
                            }
                        }else if(makerid.equals("3025")){   // lexus
                            if (lineUpid.equals("10")){
                                yearSpinnerItems.add(new CommonSpinner("10","10~"));
                            } else if(lineUpid.equals("50")){
                                yearSpinnerItems.add(new CommonSpinner("10","98~05"));
                                yearSpinnerItems.add(new CommonSpinner("20","05~13"));
                                yearSpinnerItems.add(new CommonSpinner("30","13~"));
                            } else if(lineUpid.equals("20")){
                                yearSpinnerItems.add(new CommonSpinner("10","01~06"));
                                yearSpinnerItems.add(new CommonSpinner("20","06~12"));
                                yearSpinnerItems.add(new CommonSpinner("30","12~18"));
                                yearSpinnerItems.add(new CommonSpinner("40","18~"));
                            } else if(lineUpid.equals("30")){
                                yearSpinnerItems.add(new CommonSpinner("10","97~04"));
                                yearSpinnerItems.add(new CommonSpinner("20","05~12"));
                                yearSpinnerItems.add(new CommonSpinner("30","12~19"));
                            } else if(lineUpid.equals("70")){
                                yearSpinnerItems.add(new CommonSpinner("10","00~06"));
                                yearSpinnerItems.add(new CommonSpinner("20","06~16"));
                                yearSpinnerItems.add(new CommonSpinner("30","18~"));
                            }
                        } else if (makerid.equals("3012")){ //genesis
                            if (lineUpid.equals("05")){
                                yearSpinnerItems.add(new CommonSpinner("10","17 ~"));
                            } else if (lineUpid.equals("10")){
                                yearSpinnerItems.add(new CommonSpinner("10","16 ~"));
                            } else if (lineUpid.equals("20")){
                                yearSpinnerItems.add(new CommonSpinner("10","15 ~"));
                            }
                        } else if(makerid.equals("3013")){      // kia
                            if (lineUpid.equals("10")){
                                yearSpinnerItems.add(new CommonSpinner("10","12~18"));
                                yearSpinnerItems.add(new CommonSpinner("20","18~"));
                            } else if (lineUpid.equals("20")){
                                yearSpinnerItems.add(new CommonSpinner("10","10~15"));
                                yearSpinnerItems.add(new CommonSpinner("20","15~19"));
                                yearSpinnerItems.add(new CommonSpinner("30","19~"));
                            } else if (lineUpid.equals("30")){
                                yearSpinnerItems.add(new CommonSpinner("10","09~16"));
                                yearSpinnerItems.add(new CommonSpinner("20","16~"));
                            } else if (lineUpid.equals("40")){
                                yearSpinnerItems.add(new CommonSpinner("10","12~18"));
                                yearSpinnerItems.add(new CommonSpinner("20","18~"));
                            } else if (lineUpid.equals("50")){
                                yearSpinnerItems.add(new CommonSpinner("10","16~"));
                            } else if (lineUpid.equals("60")){
                                yearSpinnerItems.add(new CommonSpinner("10","11~"));
                            } else if (lineUpid.equals("70")){
                                yearSpinnerItems.add(new CommonSpinner("10","05~10"));
                            } else if (lineUpid.equals("80")){
                                yearSpinnerItems.add(new CommonSpinner("10","04~11"));
                                yearSpinnerItems.add(new CommonSpinner("20","11~17"));
                                yearSpinnerItems.add(new CommonSpinner("30","17~"));
                            } else if (lineUpid.equals("90")){
                                yearSpinnerItems.add(new CommonSpinner("10","08~"));
                            } else if (lineUpid.equals("a0")){
                                yearSpinnerItems.add(new CommonSpinner("10","04~"));
                            } else if (lineUpid.equals("a2")){
                                yearSpinnerItems.add(new CommonSpinner("10","19~"));
                            } else if (lineUpid.equals("a4")){
                                yearSpinnerItems.add(new CommonSpinner("10","17~"));
                            } else if (lineUpid.equals("b0")){
                                yearSpinnerItems.add(new CommonSpinner("10","17~"));
                            } else if (lineUpid.equals("c0")){
                                yearSpinnerItems.add(new CommonSpinner("10","04~10"));
                                yearSpinnerItems.add(new CommonSpinner("20","10~15"));
                                yearSpinnerItems.add(new CommonSpinner("30","16~"));
                            } else if (lineUpid.equals("d0")){
                                yearSpinnerItems.add(new CommonSpinner("10","03~08"));
                            } else if (lineUpid.equals("e0")){
                                yearSpinnerItems.add(new CommonSpinner("10","99~09"));
                                yearSpinnerItems.add(new CommonSpinner("20","09~14"));
                                yearSpinnerItems.add(new CommonSpinner("30","14~"));
                            } else if (lineUpid.equals("f0")){
                                yearSpinnerItems.add(new CommonSpinner("10","08~13"));
                                yearSpinnerItems.add(new CommonSpinner("20","13~19"));
                                yearSpinnerItems.add(new CommonSpinner("30","19~"));
                            } else if (lineUpid.equals("g0")){
                                yearSpinnerItems.add(new CommonSpinner("10","03~11"));
                            } else if (lineUpid.equals("h0")){
                                yearSpinnerItems.add(new CommonSpinner("10","00~05"));
                            } else if (lineUpid.equals("i0")){
                                yearSpinnerItems.add(new CommonSpinner("10","01~05"));
                                yearSpinnerItems.add(new CommonSpinner("20","05~14"));
                                yearSpinnerItems.add(new CommonSpinner("30","14~"));
                            } else if (lineUpid.equals("j0")){
                                yearSpinnerItems.add(new CommonSpinner("10","02~06"));
                                yearSpinnerItems.add(new CommonSpinner("20","06~13"));
                                yearSpinnerItems.add(new CommonSpinner("30","13~18"));
                            } else if (lineUpid.equals("k0")){
                                yearSpinnerItems.add(new CommonSpinner("10","08~13"));
                            } else if (lineUpid.equals("l0")){
                                yearSpinnerItems.add(new CommonSpinner("10","05~11"));
                                yearSpinnerItems.add(new CommonSpinner("20","11~17"));
                            }
                        } else if(makerid.equals("3014")){      // chevorlet
                            if (lineUpid.equals("20")){
                                yearSpinnerItems.add(new CommonSpinner("10","08~"));
                            } else if (lineUpid.equals("30")){
                                yearSpinnerItems.add(new CommonSpinner("10","08~"));
                            } else if (lineUpid.equals("40")){
                                yearSpinnerItems.add(new CommonSpinner("10","02~08"));
                                yearSpinnerItems.add(new CommonSpinner("20","08~11"));
                            } else if (lineUpid.equals("50")){
                                yearSpinnerItems.add(new CommonSpinner("10","05~09"));
                                yearSpinnerItems.add(new CommonSpinner("20","09~11"));
                            } else if (lineUpid.equals("60")){
                                yearSpinnerItems.add(new CommonSpinner("10","11~16"));
                                yearSpinnerItems.add(new CommonSpinner("20","16~"));
                            } else if (lineUpid.equals("80")){
                                yearSpinnerItems.add(new CommonSpinner("10","16~"));
                            } else if (lineUpid.equals("a0")){
                                yearSpinnerItems.add(new CommonSpinner("10","11~15"));
                                yearSpinnerItems.add(new CommonSpinner("20","15~"));
                            } else if (lineUpid.equals("b0")){
                                yearSpinnerItems.add(new CommonSpinner("10","11~"));
                            } else if (lineUpid.equals("c0")){
                                yearSpinnerItems.add(new CommonSpinner("10","10~15"));
                            } else if (lineUpid.equals("d0")){
                                yearSpinnerItems.add(new CommonSpinner("10","11~18"));
                            } else if (lineUpid.equals("e0")){
                                yearSpinnerItems.add(new CommonSpinner("10","06~10"));
                            } else if (lineUpid.equals("e5")){
                                yearSpinnerItems.add(new CommonSpinner("10","18~"));
                            } else if (lineUpid.equals("f0")){
                                yearSpinnerItems.add(new CommonSpinner("10","15~"));
                            } else if (lineUpid.equals("h0")){
                                yearSpinnerItems.add(new CommonSpinner("10","11~15"));
                                yearSpinnerItems.add(new CommonSpinner("20","16~"));
                            } else if (lineUpid.equals("j0")){
                                yearSpinnerItems.add(new CommonSpinner("10","11~18"));
                            } else if (lineUpid.equals("j5")){
                                yearSpinnerItems.add(new CommonSpinner("10","19~"));
                            } else if (lineUpid.equals("k0")){
                                yearSpinnerItems.add(new CommonSpinner("10","10~17"));
                                yearSpinnerItems.add(new CommonSpinner("20","17~18"));
                            } else if (lineUpid.equals("l0")){
                                yearSpinnerItems.add(new CommonSpinner("10","06~10"));
                            } else if (lineUpid.equals("l5")){
                                yearSpinnerItems.add(new CommonSpinner("10","19~"));
                            } else if (lineUpid.equals("m0")){
                                yearSpinnerItems.add(new CommonSpinner("10","13~"));
                            }
                        } else if (makerid.equals("3015")){     // renault_samsung
                            if (lineUpid.equals("10")){
                                yearSpinnerItems.add(new CommonSpinner("10","02~11(1세대)"));
                                yearSpinnerItems.add(new CommonSpinner("20","09~(2세대)"));
                            } else if (lineUpid.equals("20")){
                                yearSpinnerItems.add(new CommonSpinner("10","98~05"));
                                yearSpinnerItems.add(new CommonSpinner("20","05~10"));
                                yearSpinnerItems.add(new CommonSpinner("30","10~19"));
                            } else if (lineUpid.equals("30")){
                                yearSpinnerItems.add(new CommonSpinner("10","16~"));
                            } else if (lineUpid.equals("40")){
                                yearSpinnerItems.add(new CommonSpinner("10","04~11"));
                                yearSpinnerItems.add(new CommonSpinner("20","11~"));
                            } else if (lineUpid.equals("50")){
                                yearSpinnerItems.add(new CommonSpinner("10","13~"));
                            } else if (lineUpid.equals("60")){
                                yearSpinnerItems.add(new CommonSpinner("10","07~15"));
                            } else if (lineUpid.equals("70")){
                                yearSpinnerItems.add(new CommonSpinner("10","16~"));
                            } else if (lineUpid.equals("72")){
                                yearSpinnerItems.add(new CommonSpinner("10","18~"));
                            } else if (lineUpid.equals("75")){
                                yearSpinnerItems.add(new CommonSpinner("10","18~"));
                            } else if (lineUpid.equals("80")){
                                yearSpinnerItems.add(new CommonSpinner("10","12~"));
                            }
                        } else if (makerid.equals("3016")){     // SsangYoung
                            if (lineUpid.equals("10")){
                                yearSpinnerItems.add(new CommonSpinner("10","01~06"));
                                yearSpinnerItems.add(new CommonSpinner("20","06~12"));
                                yearSpinnerItems.add(new CommonSpinner("30","12~17"));
                                yearSpinnerItems.add(new CommonSpinner("40","17~"));
                            } else if (lineUpid.equals("15")){
                                yearSpinnerItems.add(new CommonSpinner("10","18~"));
                            } else if (lineUpid.equals("20")){
                                yearSpinnerItems.add(new CommonSpinner("10","04~13"));
                            } else if (lineUpid.equals("30")){
                                yearSpinnerItems.add(new CommonSpinner("10","93~06"));
                            } else if (lineUpid.equals("40")){
                                yearSpinnerItems.add(new CommonSpinner("10","05~11"));
                            } else if (lineUpid.equals("50")){
                                yearSpinnerItems.add(new CommonSpinner("10","97~11(1세대)"));
                                yearSpinnerItems.add(new CommonSpinner("10","08~17(2세대)"));
                            } else if (lineUpid.equals("60")){
                                yearSpinnerItems.add(new CommonSpinner("10","05~11"));
                            } else if (lineUpid.equals("70")){
                                yearSpinnerItems.add(new CommonSpinner("10","96~05(2세대)"));
                                yearSpinnerItems.add(new CommonSpinner("10","11~19(3세대korando-c"));
                                yearSpinnerItems.add(new CommonSpinner("10","19~(4세대)"));
                            } else if (lineUpid.equals("80")){
                                yearSpinnerItems.add(new CommonSpinner("10","12~19"));
                            } else if (lineUpid.equals("90")){
                                yearSpinnerItems.add(new CommonSpinner("10","13~19"));
                            } else if (lineUpid.equals("a0")){
                                yearSpinnerItems.add(new CommonSpinner("10","15~"));
                            } else if (lineUpid.equals("b0")){
                                yearSpinnerItems.add(new CommonSpinner("10","16~"));
                            }
                        } else if (makerid.equals("3021")){     // bmw
                            if (lineUpid.equals("10")){
                                yearSpinnerItems.add(new CommonSpinner("10","04~11"));
                                yearSpinnerItems.add(new CommonSpinner("20","12~19"));
                                yearSpinnerItems.add(new CommonSpinner("30","19~"));
                            } else if (lineUpid.equals("20")){
                                yearSpinnerItems.add(new CommonSpinner("10","13~"));
                            } else if (lineUpid.equals("30")){
                                yearSpinnerItems.add(new CommonSpinner("10","98~05"));
                                yearSpinnerItems.add(new CommonSpinner("20","05~11"));
                                yearSpinnerItems.add(new CommonSpinner("30","12~19"));
                                yearSpinnerItems.add(new CommonSpinner("40","19~"));
                            } else if (lineUpid.equals("40")){
                                yearSpinnerItems.add(new CommonSpinner("10","14~"));
                            } else if (lineUpid.equals("50")){
                                yearSpinnerItems.add(new CommonSpinner("10","95~03"));
                                yearSpinnerItems.add(new CommonSpinner("20","03~09"));
                                yearSpinnerItems.add(new CommonSpinner("30","10~17"));
                                yearSpinnerItems.add(new CommonSpinner("40","17~"));
                            } else if (lineUpid.equals("60")){
                                yearSpinnerItems.add(new CommonSpinner("10","03~10"));
                                yearSpinnerItems.add(new CommonSpinner("20","11~17"));
                                yearSpinnerItems.add(new CommonSpinner("30","17~"));
                            } else if (lineUpid.equals("70")){
                                yearSpinnerItems.add(new CommonSpinner("10","01~08"));
                                yearSpinnerItems.add(new CommonSpinner("20","08~15"));
                                yearSpinnerItems.add(new CommonSpinner("30","15~"));
                            } else if (lineUpid.equals("80")){
                                yearSpinnerItems.add(new CommonSpinner("10","18~"));
                            } else if (lineUpid.equals("90")){
                                yearSpinnerItems.add(new CommonSpinner("10","11~19"));
                            } else if (lineUpid.equals("a0")){
                                yearSpinnerItems.add(new CommonSpinner("10","16~"));
                            } else if (lineUpid.equals("b0")){
                                yearSpinnerItems.add(new CommonSpinner("10","00~07"));
                                yearSpinnerItems.add(new CommonSpinner("20","07~14"));
                                yearSpinnerItems.add(new CommonSpinner("30","14~18"));
                            } else if (lineUpid.equals("c0")){
                                yearSpinnerItems.add(new CommonSpinner("10","14~"));
                            } else if (lineUpid.equals("d0")){
                                yearSpinnerItems.add(new CommonSpinner("10","99~03"));
                                yearSpinnerItems.add(new CommonSpinner("20","05~10"));
                                yearSpinnerItems.add(new CommonSpinner("30","11~17"));
                                yearSpinnerItems.add(new CommonSpinner("40","18~"));
                            } else if (lineUpid.equals("e0")){
                                yearSpinnerItems.add(new CommonSpinner("10","05~10"));
                                yearSpinnerItems.add(new CommonSpinner("20","12~18"));
                            } else if (lineUpid.equals("e5")){
                                yearSpinnerItems.add(new CommonSpinner("10","19~"));
                            } else if (lineUpid.equals("f0")){
                                yearSpinnerItems.add(new CommonSpinner("10","09~15"));
                                yearSpinnerItems.add(new CommonSpinner("20","16~"));
                            } else if (lineUpid.equals("f5")){
                                yearSpinnerItems.add(new CommonSpinner("10","18~"));
                            } else if (lineUpid.equals("g0")){
                                yearSpinnerItems.add(new CommonSpinner("10","04~10"));
                                yearSpinnerItems.add(new CommonSpinner("20","11~17"));
                                yearSpinnerItems.add(new CommonSpinner("30","18~"));
                            } else if (lineUpid.equals("h0")){
                                yearSpinnerItems.add(new CommonSpinner("10","14~18"));
                                yearSpinnerItems.add(new CommonSpinner("20","18~"));
                            } else if (lineUpid.equals("i0")){
                                yearSpinnerItems.add(new CommonSpinner("10","99~06"));
                                yearSpinnerItems.add(new CommonSpinner("20","07~13"));
                                yearSpinnerItems.add(new CommonSpinner("30","14~18"));
                                yearSpinnerItems.add(new CommonSpinner("40","18~"));
                            } else if (lineUpid.equals("j0")){
                                yearSpinnerItems.add(new CommonSpinner("10","08~14"));
                                yearSpinnerItems.add(new CommonSpinner("20","14~"));
                            } else if (lineUpid.equals("l0")){
                                yearSpinnerItems.add(new CommonSpinner("10","18~"));
                            } else if (lineUpid.equals("m0")){
                                yearSpinnerItems.add(new CommonSpinner("10","95~02"));
                            } else if (lineUpid.equals("n0")){
                                yearSpinnerItems.add(new CommonSpinner("10","02~09"));
                                yearSpinnerItems.add(new CommonSpinner("20","09~16"));
                                yearSpinnerItems.add(new CommonSpinner("30","19~"));
                            } else if (lineUpid.equals("o0")){
                                yearSpinnerItems.add(new CommonSpinner("10","99~03"));
                            } else if (lineUpid.equals("r0")){
                                yearSpinnerItems.add(new CommonSpinner("10","14~"));
                            } else if (lineUpid.equals("s0")){
                                yearSpinnerItems.add(new CommonSpinner("10","15~"));
                            }
                        }else if (makerid.equals("3028")){     // 닛산
                            if (lineUpid.equals("20")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(01-)"));
                            } else if (lineUpid.equals("30")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(07-13)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(13-)"));
                            } else if (lineUpid.equals("40")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(10-17)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(17-)"));
                            } else if (lineUpid.equals("50")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(15-)"));
                            } else if (lineUpid.equals("60")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(02-07)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(07-14)"));
                                yearSpinnerItems.add(new CommonSpinner("30","3세대(14-)"));
                            } else if (lineUpid.equals("70")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(06-12)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(12-18)"));
                                yearSpinnerItems.add(new CommonSpinner("30","3세대(18-)"));
                            } else if (lineUpid.equals("80")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(10-18)"));
                            } else if (lineUpid.equals("90")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(06-13)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(13-)"));
                            } else if (lineUpid.equals("a0")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(02-09)"));
                                yearSpinnerItems.add(new CommonSpinner("20","3세대(09-)"));
                            } else if (lineUpid.equals("b0")){
                                yearSpinnerItems.add(new CommonSpinner("10","4세대(12-)"));
                            }
                        }else if (makerid.equals("302a")){     // 랜드로버
                            if (lineUpid.equals("10")){
                                yearSpinnerItems.add(new CommonSpinner("10","3세대(04-09)"));
                                yearSpinnerItems.add(new CommonSpinner("20","4세대(09-17)"));
                                yearSpinnerItems.add(new CommonSpinner("30","5세대(17-)"));
                            } else if (lineUpid.equals("20")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(14-)"));
                            } else if (lineUpid.equals("30")){
                                yearSpinnerItems.add(new CommonSpinner("10","3세대(02-12)"));
                                yearSpinnerItems.add(new CommonSpinner("20","4세대(12-)"));
                            } else if (lineUpid.equals("40")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(17-)"));
                            } else if (lineUpid.equals("50")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(05-13)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(13-)"));
                            } else if (lineUpid.equals("60")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(11-18)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(18-)"));
                            } else if (lineUpid.equals("70")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(06-14)"));
                            }
                        }else if (makerid.equals("302c")){     // 링컨
                            if (lineUpid.equals("10")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(00-06)"));
                            } else if (lineUpid.equals("30")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(09-16)"));
                            } else if (lineUpid.equals("40")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(07-15)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(16-18)"));
                            } else if (lineUpid.equals("50")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(05-12)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(12-)"));
                            } else if (lineUpid.equals("55")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(18-)"));
                            } else if (lineUpid.equals("60")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(03-05)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(19-)"));
                            } else if (lineUpid.equals("70")){
                                yearSpinnerItems.add(new CommonSpinner("10","10세대(17-)"));
                            }
                        }else if (makerid.equals("302d")){     // 마세라티
                            if (lineUpid.equals("20")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(07-)"));
                            } else if (lineUpid.equals("30")){
                                yearSpinnerItems.add(new CommonSpinner("10","3세대(13-)"));
                            } else if (lineUpid.equals("40")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(17-)"));
                            } else if (lineUpid.equals("50")){
                                yearSpinnerItems.add(new CommonSpinner("10","5세대(03-12)"));
                                yearSpinnerItems.add(new CommonSpinner("20","6세대(13-)"));
                            }
                        } else if (makerid.equals("3026")){     // 미니
                            if (lineUpid.equals("20")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(05-08)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(09-15)"));
                                yearSpinnerItems.add(new CommonSpinner("30","3세대(16-)"));
                            } else if (lineUpid.equals("30")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(11-16)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(17-)"));
                            } else if (lineUpid.equals("40")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(12-15)"));
                            } else if (lineUpid.equals("50")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(08-14)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(15-)"));
                            } else if (lineUpid.equals("70")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(01-06)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(07-14)"));
                                yearSpinnerItems.add(new CommonSpinner("30","3세대(14-)"));
                            }
                        } else if (makerid.equals("3022")){     // 벤츠
                            if (lineUpid.equals("10")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(04-12)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(13-18)"));
                                yearSpinnerItems.add(new CommonSpinner("30","3세대(18-)"));
                            } else if (lineUpid.equals("20")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(05-11)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(12-19)"));
                                yearSpinnerItems.add(new CommonSpinner("30","3세대(19-)"));
                            } else if (lineUpid.equals("30")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(00-07)"));
                                yearSpinnerItems.add(new CommonSpinner("20","3세대(07-14)"));
                                yearSpinnerItems.add(new CommonSpinner("30","4세대(14-)"));
                            } else if (lineUpid.equals("40")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(99-06)"));
                                yearSpinnerItems.add(new CommonSpinner("20","3세대(06-13)"));
                            } else if (lineUpid.equals("50")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(14-18)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(19-)"));
                            } else if (lineUpid.equals("60")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(96-03)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(03-09)"));
                            } else if (lineUpid.equals("70")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(04-10)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(10-18)"));
                                yearSpinnerItems.add(new CommonSpinner("30","3세대(19-)"));
                            } else if (lineUpid.equals("80")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(95-02)"));
                                yearSpinnerItems.add(new CommonSpinner("20","3세대(02-09)"));
                                yearSpinnerItems.add(new CommonSpinner("30","4세대(09-16)"));
                                yearSpinnerItems.add(new CommonSpinner("40","5세대(16-)"));
                            } else if (lineUpid.equals("85")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(19-)"));
                            } else if (lineUpid.equals("90")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(09-18)"));
                                yearSpinnerItems.add(new CommonSpinner("20","3세대(18-)"));
                            } else if (lineUpid.equals("b0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(14-)"));
                            } else if (lineUpid.equals("c0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(16-)"));
                            } else if (lineUpid.equals("d0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(16-19)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(19-)"));
                            } else if (lineUpid.equals("e0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(09-15)"));
                            } else if (lineUpid.equals("f0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(16-)"));
                            } else if (lineUpid.equals("g0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(97-05)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(05-12)"));
                                yearSpinnerItems.add(new CommonSpinner("30","3세대(11-16)"));
                            } else if (lineUpid.equals("i0")){
                                yearSpinnerItems.add(new CommonSpinner("10","3세대(91-99)"));
                                yearSpinnerItems.add(new CommonSpinner("20","4세대(98-06)"));
                                yearSpinnerItems.add(new CommonSpinner("30","5세대(06-13)"));
                                yearSpinnerItems.add(new CommonSpinner("40","6세대(14-)"));
                            } else if (lineUpid.equals("j0")){
                                yearSpinnerItems.add(new CommonSpinner("10","5세대(02-12)"));
                                yearSpinnerItems.add(new CommonSpinner("20","6세대(12-)"));
                            } else if (lineUpid.equals("k0")){
                                yearSpinnerItems.add(new CommonSpinner("10","3세대(16-)"));
                            } else if (lineUpid.equals("l0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(96-04)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(04-10)"));
                                yearSpinnerItems.add(new CommonSpinner("30","3세대(10-16)"));
                            } else if (lineUpid.equals("o0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(14-)"));
                            }
                        } else if (makerid.equals("302f")){     // 벤틀리
                            if (lineUpid.equals("10")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(09-)"));
                            } else if (lineUpid.equals("15")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(16-)"));
                            } else if (lineUpid.equals("20")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(03-11)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(11-17)"));
                                yearSpinnerItems.add(new CommonSpinner("30","3세대(17-)"));
                            } else if (lineUpid.equals("30")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(05-13)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(13-19)"));
                                yearSpinnerItems.add(new CommonSpinner("30","3세대(19-)"));
                            }
                        } else if (makerid.equals("302g")){     // 볼보
                            if (lineUpid.equals("10")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(07-13)"));
                            } else if (lineUpid.equals("20")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(06-13)"));
                            } else if (lineUpid.equals("30")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(04-12)"));
                            } else if (lineUpid.equals("40")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(00-09)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(10-18)"));
                                yearSpinnerItems.add(new CommonSpinner("30","3세대(18-)"));
                            } else if (lineUpid.equals("50")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(97-00)"));
                            } else if (lineUpid.equals("60")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(98-06)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(06-16)"));
                            } else if (lineUpid.equals("70")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(16-)"));
                            } else if (lineUpid.equals("80")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(13-)"));
                            } else if (lineUpid.equals("90")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(04-12)"));
                            } else if (lineUpid.equals("a0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(11-18)"));
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(18-)"));
                            } else if (lineUpid.equals("b0")){
                                yearSpinnerItems.add(new CommonSpinner("10","3세대(07-16)"));
                            } else if (lineUpid.equals("d0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(08-17)"));
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(17-)"));
                            } else if (lineUpid.equals("e0")){
                                yearSpinnerItems.add(new CommonSpinner("10","3세대(07-16)"));
                            } else if (lineUpid.equals("f0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(02-15)"));
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(15-)"));
                            }
                        }else if (makerid.equals("302j")){     // 시트로엥
                            if (lineUpid.equals("03")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(17-)"));
                            } else if (lineUpid.equals("07")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(18-)"));
                            } else if (lineUpid.equals("10")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(14-)"));
                            } else if (lineUpid.equals("20")){
                                yearSpinnerItems.add(new CommonSpinner("30","2세대(13-17)"));
                            } else if (lineUpid.equals("25")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(17-)"));
                            } else if (lineUpid.equals("30")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(09-18)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(19-)"));
                            } else if (lineUpid.equals("40")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(10-18)"));
                            } else if (lineUpid.equals("50")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(10-18)"));
                            } else if (lineUpid.equals("60")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(19-)"));
                            }
                        } else if (makerid.equals("3023")){     // 아우디
                            if (lineUpid.equals("10")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(10-)"));
                            } else if (lineUpid.equals("20")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(03-12)"));
                                yearSpinnerItems.add(new CommonSpinner("20","3세대(12-)"));
                            } else if (lineUpid.equals("30")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(01-04)"));
                                yearSpinnerItems.add(new CommonSpinner("20","3세대(04-08)"));
                                yearSpinnerItems.add(new CommonSpinner("30","4세대(09-15)"));
                                yearSpinnerItems.add(new CommonSpinner("40","5세대(15-)"));
                            } else if (lineUpid.equals("40")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(07-16)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(16-)"));
                            } else if (lineUpid.equals("50")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(97-04)"));
                                yearSpinnerItems.add(new CommonSpinner("20","3세대(04-11)"));
                                yearSpinnerItems.add(new CommonSpinner("30","4세대(11-19)"));
                                yearSpinnerItems.add(new CommonSpinner("40","5세대(19-)"));
                            } else if (lineUpid.equals("60")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(10-17)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(18-)"));
                            } else if (lineUpid.equals("70")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(02-09)"));
                                yearSpinnerItems.add(new CommonSpinner("20","3세대(09-17)"));
                                yearSpinnerItems.add(new CommonSpinner("30","4세대(18-)"));
                            } else if (lineUpid.equals("80")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(06-13)"));
                                yearSpinnerItems.add(new CommonSpinner("20","3세대(13-)"));
                            } else if (lineUpid.equals("90")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(03-05)"));
                                yearSpinnerItems.add(new CommonSpinner("20","3세대(05-09)"));
                                yearSpinnerItems.add(new CommonSpinner("30","4세대(09-16)"));
                                yearSpinnerItems.add(new CommonSpinner("40","5세대(16-)"));
                            } else if (lineUpid.equals("a0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(07-16)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(16-)"));
                            } else if (lineUpid.equals("b0")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(99-03)"));
                                yearSpinnerItems.add(new CommonSpinner("20","3세대(06-11)"));
                                yearSpinnerItems.add(new CommonSpinner("30","4세대(12-19)"));
                                yearSpinnerItems.add(new CommonSpinner("40","5세대(19-)"));
                            } else if (lineUpid.equals("c0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(12-)"));
                            } else if (lineUpid.equals("d0")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(02-12)"));
                                yearSpinnerItems.add(new CommonSpinner("20","3세대(12-)"));
                            } else if (lineUpid.equals("e0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(98-06)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(06-14)"));
                                yearSpinnerItems.add(new CommonSpinner("30","3세대(14-)"));
                            } else if (lineUpid.equals("f0")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(06-14)"));
                                yearSpinnerItems.add(new CommonSpinner("20","3세대(14-)"));
                            } else if (lineUpid.equals("g0")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(06-14)"));
                                yearSpinnerItems.add(new CommonSpinner("20","3세대(14-)"));
                            } else if (lineUpid.equals("h0")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(03-12)"));
                                yearSpinnerItems.add(new CommonSpinner("20","3세대(12-)"));
                            } else if (lineUpid.equals("i0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(06-08)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(12-15)"));
                                yearSpinnerItems.add(new CommonSpinner("30","3세대(18-)"));
                            } else if (lineUpid.equals("j0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(10-16)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(16-)"));
                            } else if (lineUpid.equals("k0")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(02-04)"));
                                yearSpinnerItems.add(new CommonSpinner("20","3세대(08-10)"));
                                yearSpinnerItems.add(new CommonSpinner("20","4세대(12-)"));
                            } else if (lineUpid.equals("l0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(13-)"));
                            } else if (lineUpid.equals("m0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(11-)"));
                            } else if (lineUpid.equals("n0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(08-17)"));
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(17-)"));
                            } else if (lineUpid.equals("o0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(05-15)"));
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(15-)"));
                            } else if (lineUpid.equals("p0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(13-17)"));
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(17-)"));
                            } else if (lineUpid.equals("q0")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(15-)"));
                            } else if (lineUpid.equals("r0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(13-)"));
                            } else if (lineUpid.equals("s0")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(07-15)"));
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(15-)"));
                            }
                        } else if (makerid.equals("302k")) {     // 인피니티
                            if (lineUpid.equals("10")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(08-12)"));
                            } else if (lineUpid.equals("20")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "2세대(03-08)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "3세대(08-13)"));
                            } else if (lineUpid.equals("30")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "3세대(02-07)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "4세대(06-15)"));
                            } else if (lineUpid.equals("40")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(12-13)"));
                            } else if (lineUpid.equals("50")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "4세대(04-10)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "5세대(10-12)"));
                            } else if (lineUpid.equals("60")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(15-)"));
                            } else if (lineUpid.equals("70")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "4세대(14-)"));
                            } else if (lineUpid.equals("80")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "cv36(14-17)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "cv37(17-)"));
                            } else if (lineUpid.equals("90")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "4세대(13-19)"));
                            } else if (lineUpid.equals("a0")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(16-)"));
                            } else if (lineUpid.equals("b0")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(13-18)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "2세대(18-)"));
                            } else if (lineUpid.equals("c0")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(13-)"));
                            } else if (lineUpid.equals("d0")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "2세대(13-18)"));
                            } else if (lineUpid.equals("e0")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(10-)"));
                            }
                        } else if (makerid.equals("302l")) {     // 재규어
                            if (lineUpid.equals("05")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(18-)"));
                            } else if (lineUpid.equals("10")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(13-)"));
                            } else if (lineUpid.equals("20")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(16-)"));
                            } else if (lineUpid.equals("25")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(18-)"));
                            } else if (lineUpid.equals("30")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(00-08)"));
                            } else if (lineUpid.equals("40")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(01-09)"));
                            } else if (lineUpid.equals("50")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(15-)"));
                            } else if (lineUpid.equals("60")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(08-15)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "2세대(16-)"));
                            } else if (lineUpid.equals("70")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "4세대(03-09)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "5세대(09-)"));
                            } else if (lineUpid.equals("80")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "2세대(06-14)"));
                            }
                        } else if (makerid.equals("302m")){     // Jeep
                            if (lineUpid.equals("10")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(99-05)"));
                                yearSpinnerItems.add(new CommonSpinner("20","3세대(05-10)"));
                                yearSpinnerItems.add(new CommonSpinner("30","4세대(10-)"));
                            } else if (lineUpid.equals("20")){
                                yearSpinnerItems.add(new CommonSpinner("10","2세대(97-06)"));
                                yearSpinnerItems.add(new CommonSpinner("20","3세대(07-18)"));
                                yearSpinnerItems.add(new CommonSpinner("30","4세대(18-)"));
                            } else if (lineUpid.equals("30")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(14-)"));
                            } else if (lineUpid.equals("40")){
                                yearSpinnerItems.add(new CommonSpinner("10","3세대(02-07)"));
                                yearSpinnerItems.add(new CommonSpinner("20","5세대(13-)"));
                            } else if (lineUpid.equals("45")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(06-10)"));
                            } else if (lineUpid.equals("50")){
                                yearSpinnerItems.add(new CommonSpinner("10","1세대(07-17)"));
                                yearSpinnerItems.add(new CommonSpinner("20","2세대(17-)"));
                            }
                        } else if (makerid.equals("302n")) {     // 캐딜락
                            if (lineUpid.equals("10")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(13-)"));
                            } else if (lineUpid.equals("20")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "2세대(05-09)"));
                            } else if (lineUpid.equals("30")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(16-)"));
                            } else if (lineUpid.equals("40")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(03-07)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "2세대(08-14)"));
                                yearSpinnerItems.add(new CommonSpinner("30", "3세대(14-)"));
                            } else if (lineUpid.equals("50")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(06-11)"));
                            } else if (lineUpid.equals("60")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(04-09)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "2세대(10-16)"));
                            } else if (lineUpid.equals("70")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(04-07)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "2세대(08-12)"));
                            } else if (lineUpid.equals("80")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(16-)"));
                            } else if (lineUpid.equals("90")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "8세대(00-05)"));
                            } else if (lineUpid.equals("a0")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "5세대(98-04)"));
                            } else if (lineUpid.equals("b0")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "3세대(06-14)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "4세대(15-)"));
                            }
                        } else if (makerid.equals("302p")) {     // 토요타
                            if (lineUpid.equals("10")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(12-)"));
                            } else if (lineUpid.equals("20")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(06-16)"));
                            } else if (lineUpid.equals("30")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "3세대(05-13)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "4세대(13-18)"));
                                yearSpinnerItems.add(new CommonSpinner("30", "5세대(19-)"));
                            } else if (lineUpid.equals("40")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(08-15)"));
                            } else if (lineUpid.equals("50")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "3세대(11-)"));
                            } else if (lineUpid.equals("60")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "3세대(05-12)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "4세대(12-18)"));
                                yearSpinnerItems.add(new CommonSpinner("30", "5세대(18-)"));
                            } else if (lineUpid.equals("70")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "6세대(06-11)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "7세대(11-14)"));
                                yearSpinnerItems.add(new CommonSpinner("30", "8세대(15-17)"));
                                yearSpinnerItems.add(new CommonSpinner("40", "9세대(18-)"));
                            } else if (lineUpid.equals("80")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "10세대(06-13)"));
                            } else if (lineUpid.equals("90")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "3세대(09-15)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "4세대(15-)"));
                            }
                        } else if (makerid.equals("3027")) {     // 포드
                            if (lineUpid.equals("10")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "11세대(04-08)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "12세대(09-14)"));
                                yearSpinnerItems.add(new CommonSpinner("30", "13세대(15-)"));
                            } else if (lineUpid.equals("20")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "4세대(94-04)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "5세대(05-14)"));
                                yearSpinnerItems.add(new CommonSpinner("30", "6세대(15-)"));
                            } else if (lineUpid.equals("30")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "2세대(00-07)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "3세대(07-14)"));
                                yearSpinnerItems.add(new CommonSpinner("30", "4세대(14-)"));
                            } else if (lineUpid.equals("40")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(00-06)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "2세대(07-12)"));
                                yearSpinnerItems.add(new CommonSpinner("30", "3세대(12-19)"));
                                yearSpinnerItems.add(new CommonSpinner("40", "4세대(19-)"));
                            } else if (lineUpid.equals("50")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "5세대(10-19)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "6세대(19-)"));
                            } else if (lineUpid.equals("60")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(08-12)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "2세대(12-)"));
                            } else if (lineUpid.equals("70")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "4세대(00-07)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "5세대(07-09)"));
                                yearSpinnerItems.add(new CommonSpinner("30", "6세대(09-19)"));
                            } else if (lineUpid.equals("80")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(04-07)"));
                            } else if (lineUpid.equals("90")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "3세대(11-18)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "4세대(18-)"));
                            } else if (lineUpid.equals("a0")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(05-12)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "2세대(12-)"));
                            }
                        } else if (makerid.equals("302q")) {     // 포르쉐
                            if (lineUpid.equals("10")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(05-12)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "2세대(12-18)"));
                                yearSpinnerItems.add(new CommonSpinner("30", "3세대(19-)"));
                            } else if (lineUpid.equals("20")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(14-)"));
                            } else if (lineUpid.equals("30")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "2세대(05-12)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "3세대(12-16)"));
                                yearSpinnerItems.add(new CommonSpinner("30", "4세대(16-)"));
                            } else if (lineUpid.equals("40")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "2세대(05-12)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "3세대(12-16)"));
                                yearSpinnerItems.add(new CommonSpinner("30", "4세대(17-)"));
                            } else if (lineUpid.equals("50")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(02-06)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "1.5세대(07-10)"));
                                yearSpinnerItems.add(new CommonSpinner("30", "2세대(11-17)"));
                                yearSpinnerItems.add(new CommonSpinner("40", "3세대(17-)"));
                            } else if (lineUpid.equals("60")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(09-16)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "2세대(16-)"));
                            }
                        } else if (makerid.equals("3024")) {     // 폭스바겐
                            if (lineUpid.equals("10")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(08-17)"));
                            } else if (lineUpid.equals("20")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(06-14)"));
                            } else if (lineUpid.equals("30")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "4세대(97-03)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "5세대(03-08)"));
                                yearSpinnerItems.add(new CommonSpinner("30", "6세대(08-12)"));
                                yearSpinnerItems.add(new CommonSpinner("40", "7세대(13-)"));
                            } else if (lineUpid.equals("40")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "2세대(97-11)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "3세대(11-19)"));
                            } else if (lineUpid.equals("50")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "3세대(08-18)"));
                            } else if (lineUpid.equals("55")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(18-)"));
                            } else if (lineUpid.equals("60")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "5세대(05-11)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "6세대(11-17)"));
                                yearSpinnerItems.add(new CommonSpinner("30", "7세대(18-)"));
                            } else if (lineUpid.equals("70")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "6세대(05-11)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "7세대(11-15)"));
                                yearSpinnerItems.add(new CommonSpinner("30", "8세대(15-)"));
                            } else if (lineUpid.equals("80")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(02-16)"));
                            } else if (lineUpid.equals("90")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "5세대(09-17)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "6세대(18-)"));
                            } else if (lineUpid.equals("a0")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(02-10)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "2세대(10-18)"));
                                yearSpinnerItems.add(new CommonSpinner("30", "3세대(18-)"));
                            } else if (lineUpid.equals("b0")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(07-15)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "2세대(15-)"));
                            }
                        } else if (makerid.equals("302r")) {     // 푸조
                            if (lineUpid.equals("10")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(98-12)"));
                            } else if (lineUpid.equals("20")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(06-14)"));
                            } else if (lineUpid.equals("30")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(12-19)"));
                            } else if (lineUpid.equals("40")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(01-08)"));
                            } else if (lineUpid.equals("50")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(08-13)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "2세대(13-)"));
                            } else if (lineUpid.equals("60")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(04-10)"));
                            } else if (lineUpid.equals("70")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(11-18)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "2세대(18-)"));
                            } else if (lineUpid.equals("80")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(99-10)"));
                            } else if (lineUpid.equals("90")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(13-)"));
                            } else if (lineUpid.equals("a0")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(08-16)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "2세대(16-)"));
                            } else if (lineUpid.equals("a5")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(09-16)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "2세대(17-)"));
                            } else if (lineUpid.equals("b0")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(09-15)"));
                            }
                        } else if (makerid.equals("302u")) {     // 혼다
                            if (lineUpid.equals("10")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "3세대(07-11)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "4세대(11-16)"));
                                yearSpinnerItems.add(new CommonSpinner("30", "5세대(17-)"));
                            } else if (lineUpid.equals("20")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(10-16)"));
                            } else if (lineUpid.equals("30")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "2세대(14-)"));
                            } else if (lineUpid.equals("40")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "4세대(04-12)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "5세대(14-)"));
                            } else if (lineUpid.equals("50")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "8세대(05-11)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "9세대(11-15)"));
                                yearSpinnerItems.add(new CommonSpinner("30", "10세대(15-)"));
                            } else if (lineUpid.equals("60")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "7세대(02-07)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "8세대(08-12)"));
                                yearSpinnerItems.add(new CommonSpinner("30", "9세대(12-17)"));
                                yearSpinnerItems.add(new CommonSpinner("40", "10세대(18-)"));
                            } else if (lineUpid.equals("70")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "4세대(11-17)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "5세대(18-)"));
                            } else if (lineUpid.equals("80")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "2세대(09-14)"));
                            } else if (lineUpid.equals("90")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "1세대(10-15)"));
                            } else if (lineUpid.equals("a0")) {
                                yearSpinnerItems.add(new CommonSpinner("10", "2세대(08-15)"));
                                yearSpinnerItems.add(new CommonSpinner("20", "3세대(16-)"));
                            }
                        }

                        ArrayAdapter<CommonSpinner> yearIdAdapter = new ArrayAdapter<CommonSpinner>(context,android.R.layout.simple_spinner_item,yearSpinnerItems);
                        yearIdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
                        yearIdSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                CommonSpinner yearIdList = (CommonSpinner)parent.getItemAtPosition(position);
                                yearId = yearIdList.getKey();
                                year = yearIdList.getValue();
                                Log.d(TAG,"key:"+yearId);
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        yearIdSpinner.setAdapter(yearIdAdapter);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                lineUpIdSpinner.setAdapter(lineUpIdAdapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        makeridSpinner.setAdapter(makeridAdapter);
    }
}
