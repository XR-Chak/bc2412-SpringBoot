package com.example.demo_bc_forum.dto.Mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.demo_bc_forum.dto.CommentDTO;
import com.example.demo_bc_forum.dto.PostDTO;
import com.example.demo_bc_forum.dto.PostDTOO;
import com.example.demo_bc_forum.model.CommentDto;

public class PostDTOMapper {
  public static Map<Long,List<PostDTOO>> listmap(Map<Long,List<PostDTO>> postDTO){
    Map<Long,List<PostDTOO>> ans=new HashMap<>();

    for(Map.Entry<Long,List<PostDTO>> e: postDTO.entrySet()){
      List<PostDTOO> PDTO = new ArrayList<>();
      for(PostDTO c:e.getValue()){
        PDTO.add(c.toPostDTOO());
      }
      ans.put(e.getKey(), PDTO);
    }
    return ans;
  }
}
