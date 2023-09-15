import { KanbanCardData } from "@/src/stores/kanban/Kanban.atoms";
import classNames from "classnames";
import { useParams, useRouter } from "next/navigation";

type KanbanCardComponentType = {
  data: KanbanCardData | null;
  columnIndex: number;
  cardId?: string;
  applicantId?: string;
};

function KanbanCardComponent({
  data,
  columnIndex,
  applicantId,
  cardId,
}: KanbanCardComponentType) {
  const { generation } = useParams();
  const navigate = useRouter();

  if (!data) {
    return <></>;
  }

  const {
    id,
    title,
    apply,
    comment,
    isHearted,
    heart,
    major,
    applicantId: dataApplicantId,
    cardType,
  } = data;

  const onClickDetail = () => {
    navigate.push(
      `/kanban/${generation}/detail?applicantId=${dataApplicantId}&columnIndex=${columnIndex}&type=${cardType}&cardId=${id}`
    );
  };

  return (
    <div
      className={classNames(
        "border-[1px] w-full p-3 rounded-lg drop-shadow-md bg-white hover:border-[#7AA0FF]",
        (applicantId !== "" && dataApplicantId == applicantId) ||
          `${id}` == cardId
          ? "border-[#2160FF]"
          : "border-[#F0F0F0]"
      )}
      onClick={onClickDetail}
    >
      <div className="text-xs text-[#666666]">{major}</div>
      <div className="font-bold">{title}</div>
      <div className="mt-2 flex justify-between items-center text-sm text-[#666666]">
        <div className="text-sm">{apply.join(" / ")}</div>
        <div className="flex gap-3">
          <div className="flex">
            <img src="/icons/bubble.right.svg" alt="comment" />
            {comment}
          </div>
          <div className="flex">
            {isHearted ? (
              <img src="/icons/heart.point.svg" alt="heart" />
            ) : (
              <img src="/icons/heart.svg" alt="heart" />
            )}
            {heart}
          </div>
        </div>
      </div>
    </div>
  );
}

export default KanbanCardComponent;
