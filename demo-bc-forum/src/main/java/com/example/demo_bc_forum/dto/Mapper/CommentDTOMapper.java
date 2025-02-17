package com.example.demo_bc_forum.dto.Mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.demo_bc_forum.dto.CommentDTO;
import com.example.demo_bc_forum.model.CommentDto;

public class CommentDTOMapper {
  public static Map<Long,List<CommentDTO>> listmap(Map<Long,List<CommentDto>> MapCommentDto){
    Map<Long,List<CommentDTO>> ans=new HashMap<>();

    for(Map.Entry<Long,List<CommentDto>> e: MapCommentDto.entrySet()){
      List<CommentDTO> CDTO = new ArrayList<>();
      for(CommentDto c:e.getValue()){
        CDTO.add(c.toDTO());
      }
      ans.put(e.getKey(), CDTO);
    }
    System.out.println(ans.toString());
    return ans;
  }
}
