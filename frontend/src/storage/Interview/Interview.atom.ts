import { atom } from 'jotai';

type interviewBoardState = {
  title: string;
  apply: string[];
  score: string;
  registerDate: string;
};

export const interviewListState = atom( [] as interviewBoardState[]);

export const interviewPopupBooleanState = atom(false);
