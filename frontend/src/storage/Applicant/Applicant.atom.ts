import { atom } from 'jotai'

type applicantBoardState = {
  title: string
  apply: string[]
  semester: string
  registerDate: string
}

export const applicantListState = atom<applicantBoardState[]>([])

export const applicantPopupBooleanState = atom(false)
