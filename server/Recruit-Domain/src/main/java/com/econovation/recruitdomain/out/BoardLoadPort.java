package com.econovation.recruitdomain.out;

import com.econovation.recruitdomain.domains.board.domain.Board;
import java.util.List;

public interface BoardLoadPort {
    //    List<Board> getByHopeField(String hopeField);
    //
    Board getBoardByLocation(Integer navLoc, Integer colLoc, Integer lowLoc);

    Board getBoardById(Integer id);

    List<Board> getBoardByNavLavigationIdAndColLoc(Integer navigationId, Integer colLoc);
    //
    //    List<Board> getByNavColAndColLoc(Integer navLoc, Integer colLoc);
    //
    //    List<Board> getBoardByNavLoc(Integer navLoc);
    //
    //    List<Board> getBoardBetweenLowLoc(Integer startLowLoc, Integer destinationLowLoc);
}
