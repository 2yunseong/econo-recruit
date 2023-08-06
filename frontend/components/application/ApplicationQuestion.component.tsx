"use client";

import { FC } from "react";
import Txt from "../common/Txt.component";
import classNames from "classnames";
import { useAtomValue } from "jotai";
import { applicationIndexAtom } from "@/stores/application";
import dynamic from "next/dynamic";

interface ApplicationQuestionProps {
  className?: string;
  applicationQuestions: ApplicationQuestion[];
}

const ApplicationRadio = dynamic(
  () => import("./ApplicationNode/ApplicationRadio.component")
);

const junctionQuestion = (applicationNodeData: ApplicationNode) => {
  const jsxNode = {
    radio: <ApplicationRadio data={applicationNodeData} />,
    radioByTwoRank: "",
    radioByLayer: "",
    text: "",
    textarea: "",
    booleanTextBox: "",
    bar: "",
    justText: "",
    checkbox: "",
    checkboxWithEtc: "",
  };

  return jsxNode[applicationNodeData.type];
};

const ApplicationQuestion: FC<ApplicationQuestionProps> = ({
  className,
  applicationQuestions,
}) => {
  const applicationIndex = useAtomValue(applicationIndexAtom);
  const applicationQuestion = applicationQuestions[applicationIndex];

  return (
    <article className={classNames(className)}>
      <Txt typography="h1">신입모집 신청</Txt>
      <div className="my-6 h-1 bg-gray-300 w-full"></div>
      <div className="flex pr-12">
        <div className="flex-1">
          <div className="mb-4">
            <Txt typography="h6">{`${applicationQuestion.id}. ${
              applicationQuestion.title
            } ${applicationQuestion.require ? "*" : ""}`}</Txt>
          </div>
          {applicationQuestion.subtitle && (
            <div className="pl-4">
              <Txt className="text-sm">{applicationQuestion.subtitle}</Txt>
            </div>
          )}
        </div>
        <div className="flex-1">
          {applicationQuestion.nodes.map((node, index) => {
            return (
              <div key={index} className="my-6">
                {junctionQuestion(node)}
              </div>
            );
          })}
        </div>
      </div>
    </article>
  );
};

export default ApplicationQuestion;
