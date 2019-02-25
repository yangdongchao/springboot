package com.example.demo;

import com.example.demo.domain.Board;
import com.example.demo.service.imp.BoardServiceImp;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.demo.ueditor.*;


/**
 * @ClassName UEditorController
 * @Description TODO
 * @Auther ydc
 * @Date 2019/1/26 17:24
 * @Version 1.0
 **/
@RestController
public class UEditorController  {


    private BoardServiceImp boardServiceImp;

    @Autowired
    public void setBoardServiceImp(BoardServiceImp boardServiceImp) {
        this.boardServiceImp = boardServiceImp;
    }

    @RequestMapping("userWriting")
    public ModelAndView UEditor(){
        ModelAndView mv = new ModelAndView();
        List<Board> boards=boardServiceImp.findAllBoard();
        mv.addObject("boards",boards);
        mv.setViewName("writing");
        return mv;
    }

    @RequestMapping(value="/config")
    public void config(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
