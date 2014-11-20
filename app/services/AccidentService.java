package services;

import models.AccidentAnalysis;
import models.AccidentLetters;
import utils.CommonUtil;
import utils.ConverterUtil;
import vo.AccidentAnalysisVo;
import vo.AccidentLettersVo;
import vo.PageVo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Project: train</p>
 * <p>Title: AccidentService.java</p>
 * <p>Description: </p>
 * <p>Copyright (c) 2014 Sunlights.cc</p>
 * <p>All Rights Reserved.</p>
 *
 * @author <a href="mailto:jiaming.wang@sunlights.cc">wangJiaMing</a>
 */
public class AccidentService {

    public static void searchAccidentLetters(PageVo pageVo) {
        List<AccidentLetters> list = CommonUtil.searchEntity(pageVo, AccidentLetters.class);

        List<AccidentLettersVo> voList = new ArrayList<AccidentLettersVo>();
        for (AccidentLetters accidentLetters : list){
             AccidentLettersVo vo = new AccidentLettersVo();

            ConverterUtil.fromEntity(vo, accidentLetters);

            voList.add(vo);
        }

        pageVo.list = voList;
    }


    public static void searchAccidentAnalysis(PageVo pageVo) {
        List<AccidentAnalysis> list = CommonUtil.searchEntity(pageVo, AccidentAnalysis.class);
        List<AccidentAnalysisVo> voList = new ArrayList<AccidentAnalysisVo>();
        for (AccidentAnalysis accidentAnalysis : list){
            AccidentAnalysisVo vo = new AccidentAnalysisVo();

            ConverterUtil.fromEntity(vo, accidentAnalysis);

            voList.add(vo);
        }

        pageVo.list = voList;
    }
}
